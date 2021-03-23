package org.sunt.sqlanalysis.parser.oracle

import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.TokenStream
import org.slf4j.LoggerFactory
import org.sunt.sqlanalysis.exception.IllegalSyntaxException
import org.sunt.sqlanalysis.exception.WillNeverHappenException
import org.sunt.sqlanalysis.model.*
import org.sunt.sqlanalysis.parser.SqlParseListener
import org.sunt.sqlanalysis.parser.oracle.grammar.PlSqlParser.*
import org.sunt.sqlanalysis.parser.oracle.grammar.PlSqlParserBaseListener
import java.util.*

internal class OracleSqlParseListener(override val tokenStream: TokenStream) : PlSqlParserBaseListener(), SqlParseListener {

    override val tables: MutableList<Table> = mutableListOf()

    private var withTables: Map<String, SelectTable> = emptyMap()

    private var windowFunctions: Map<String, WindowField> = emptyMap()

    private val variables: MutableMap<String, MutableList<Expression>> = TreeMap(String.CASE_INSENSITIVE_ORDER)


    private fun getTableName(ctx: General_table_refContext): Pair<String, String> {
        return ctx.text to (ctx.table_alias()?.text?:"")
    }

    private fun getTableName(ctx: Tableview_nameContext): String {
        return ctx.text
    }
    
    override fun exitCreate_table(ctx: Create_tableContext) {
        val tableName = getTableName(ctx.tableview_name()) // maybe complex
        val createTable = CreateTable(tableName).also { it.isTemporary = ctx.TEMPORARY()!=null }
        when {
            ctx.relational_table() != null -> {
                /**
                relational_property
                : (column_definition
                | virtual_column_definition
                | out_of_line_constraint
                | out_of_line_ref_constraint
                | supplemental_logging_props
                )
                 */
                for (propCtx in ctx.relational_table().relational_property()) {
                    when {
                        propCtx.column_definition() != null -> {
                            val createField = CreateField(propCtx.getRawText())
                            with(createField) {
                                columnName = propCtx.column_definition().column_name().text
                                setPosition(propCtx.column_definition().column_name())
                                dataType = DataType.of(with(propCtx.column_definition()){
                                    datatype()?.native_datatype_element()?.text
                                        ?: type_name()?.text
                                })
                            }
                            createTable.addField(createField)
                        }
                        propCtx.virtual_column_definition() != null -> {
                            val createField = CreateField(propCtx.getRawText())
                            with(createField) {
                                columnName = propCtx.virtual_column_definition().column_name().text
                                setPosition(propCtx.column_definition().column_name())
                                dataType = DataType.of(propCtx.virtual_column_definition().datatype()?.native_datatype_element()?.text)
                            }
                            createTable.addField(createField)
                        }
                        else -> {}
                    }
                }
                if (ctx.relational_table().table_partitioning_clauses() != null) {
                    val partitionColumnNames =
                        with (ctx.relational_table().table_partitioning_clauses()) {
                        when {
                            range_partitions() != null -> range_partitions().column_name()
                            list_partitions() != null -> listOf(list_partitions().column_name())
                            hash_partitions() != null -> hash_partitions().column_name()
                            composite_range_partitions() != null -> composite_range_partitions().column_name()
                            composite_list_partitions() != null -> listOf(composite_list_partitions().column_name())
                            composite_hash_partitions() != null -> composite_hash_partitions().column_name()
                            reference_partitioning() != null -> emptyList()
                            system_partitioning() != null -> emptyList()
                            else -> throw WillNeverHappenException()
                        }
                    }
                    val partitionFields = partitionColumnNames.map { AtomicField(it.getRawText()).apply { setPosition(it) } }
                }
            }
            ctx.object_table() != null -> { log.warn("object table is not supported yet") }
            ctx.xmltype_table() != null -> { log.warn("xml table is not supported yet") }
        }
        val selectTable = ctx.select_only_statement()?.let { visitSelect_only_statement(it) }
        if (selectTable != null) {
            createTable.setSourceTable(selectTable)
        }
        this.tables.add(createTable)
    }

    override fun exitCreate_view(ctx: Create_viewContext) {
        val viewName = getTableName(ctx.tableview_name())
        val selectTable = visitSelect_only_statement(ctx.select_only_statement())
        val createTable = CreateTable(viewName).setSourceTable(selectTable).apply { isView = true }
        this.tables.add(createTable)
    }

    override fun exitCreate_materialized_view(ctx: Create_materialized_viewContext) {
        val viewName = getTableName(ctx.tableview_name())
        val selectTable = visitSelect_only_statement(ctx.select_only_statement())
        val createTable = CreateTable(viewName).setSourceTable(selectTable).apply { isMaterialized=true; isView=true }
        this.tables.add(createTable)
    }

    override fun exitSelect_statement(ctx: Select_statementContext) {
        if (ctx.parent is Data_manipulation_language_statementsContext
            || ctx.parent is Explain_statementContext ) {
            this.tables.add(visitSelect_statement(ctx))
        }
    }

    override fun exitMerge_statement(ctx: Merge_statementContext) {

    }

    override fun exitInsert_statement(ctx: Insert_statementContext) {
        fun visitInsert_into_clause(ctx: Insert_into_clauseContext): InsertTable {
            val tableNameAlias = getTableName(ctx.general_table_ref())
            val insertColumnNames = ctx
                .paren_column_list()
                ?.column_list()
                ?.column_name()
                ?.map { AtomicField(it.text).apply { setPosition(it) } }
                ?: emptyList()
            return InsertTable(tableNameAlias.first)
                .also {
                    if (insertColumnNames.isNotEmpty()) {
                        it.addFields(insertColumnNames)
                        it.isRealFields = true
                    }
                }
        }

        if (ctx.single_table_insert() != null) {
            val selectTable = ctx.single_table_insert().select_statement()?.let { visitSelect_statement(it) } ?: return
            val insertTable = visitInsert_into_clause(ctx.single_table_insert().insert_into_clause()).setSourceTable(selectTable)
            this.tables.add(insertTable)
        } else if (ctx.multi_table_insert() != null) {
            fun visitMulti_table_element(ctxs: List<Multi_table_elementContext>): List<InsertTable> {
                return ctxs.filter { it.values_clause() == null }.map { visitInsert_into_clause(it.insert_into_clause()) }
            }

            val selectTable = ctx.multi_table_insert().select_statement().let { visitSelect_statement(it) }
            if (ctx.multi_table_insert().multi_table_element()?.isNotEmpty() == true) {
                val insertTables = visitMulti_table_element(ctx.multi_table_insert().multi_table_element()).onEach { it.setSourceTable(selectTable) }
                this.tables.addAll(insertTables)
            } else if (ctx.multi_table_insert().conditional_insert_clause() != null) {
                val insertTables = visitMulti_table_element(ctx.multi_table_insert().conditional_insert_clause().conditional_insert_when_part()?.flatMap { it.multi_table_element() }?: emptyList()).toMutableList()
                ctx.multi_table_insert().conditional_insert_clause().conditional_insert_else_part()?.multi_table_element()?.also { insertTables.addAll(visitMulti_table_element(it)) }
                insertTables.onEach { it.setSourceTable(selectTable) }
                this.tables.addAll(insertTables)
            }
        }
    }

    override fun exitDelete_statement(ctx: Delete_statementContext) {
        val tableNameAlias = getTableName(ctx.general_table_ref())
        val where = ctx.where_clause()?.let { visitWhere_clause(it) }
        val deleteTable = if (where != null) DeleteTable(tableNameAlias.first, where) else DeleteTable(tableNameAlias.first)
        if ("" != tableNameAlias.second) {
            deleteTable.alias = Alias(tableNameAlias.second, false)
        }
        this.tables.add(deleteTable)
    }

    override fun exitUpdate_statement(ctx: Update_statementContext) {
        val tableNameAlias = getTableName(ctx.general_table_ref())
        val where = ctx.where_clause()?.let { visitWhere_clause(it) }
        val updateTable = if (where != null) UpdateTable(tableNameAlias.first, where) else UpdateTable(tableNameAlias.first)
        if ("" != tableNameAlias.second) {
            updateTable.alias = Alias(tableNameAlias.second, false)
        }
        this.tables.add(updateTable)
    }

    override fun exitAssignment_statement(ctx: Assignment_statementContext) {
        val expr = visitExpression(ctx.expression())
        val varName = if (ctx.general_element() != null) {
            ctx.general_element().text
        } else  {
            ctx.bind_variable().text
        }
        if (!this.variables.containsKey(varName)) {
            this.variables[varName] = LinkedList()
        }
        this.variables[varName]!!.add(expr)
    }

    private fun visitSelect_statement(ctx: Select_statementContext): SelectTable {
        val selectTable = visitSelect_only_statement(ctx.select_only_statement())
        if (ctx.order_by_clause() != null && ctx.order_by_clause().isNotEmpty()) {
            selectTable.orderBys = LinkedList()
            for (orderByClausecontext in ctx.order_by_clause()) {
                val orderByItems = orderByClausecontext.order_by_elements().map { OrderByItem(visitExpression(it.expression()), it.DESC()==null || it.ASC()!=null) }
                selectTable.orderBys.addAll(orderByItems)
            }
        }
        return selectTable
    }

    /**
     * @see com.quantchi.tablize.parser.hive.HiveSqlParseListener#visitWithClause
     */
    private fun visitSelect_only_statement(ctx: Select_only_statementContext): SelectTable {
        val originalWithTables = this.withTables
        if ( ctx.subquery_factoring_clause() != null) {
            val withTable = TreeMap<String, SelectTable>(String.CASE_INSENSITIVE_ORDER)
            val size = ctx.subquery_factoring_clause().factoring_element().size
            for (i in 0 until size*2-1) {
                val factoringElementContext = ctx.subquery_factoring_clause().factoring_element(if (i<size) i else 2*(size-1)-i)
                val withTableName = factoringElementContext.query_name().identifier().text
                withTable[withTableName] = visitSubQuery(factoringElementContext.subquery())
            }
            this.withTables = withTable
        }
        try {
            return visitSubQuery(ctx.subquery())
        } finally {
            this.withTables = originalWithTables
        }
    }

    private fun visitSubQuery(ctx: SubqueryContext): SelectTable {
        val selectTable = ctx.subquery_basic_elements().query_block()?.let { visitQuery_block(it) }
            ?:ctx.subquery_basic_elements().subquery().let { visitSubQuery(it) }
        if (ctx.subquery_operation_part()?.isNotEmpty() == true) {
            val setTable = SetOperatorTable(ctx.getRawText()).setFirstTable(selectTable)
            for (subqueryOperationPartcontext in ctx.subquery_operation_part()) {
                val setOp = if (subqueryOperationPartcontext.UNION()!=null) {
                    if (subqueryOperationPartcontext.ALL() == null)
                        SetOperator.UNION_DISTINCT
                    else
                        SetOperator.UNION_ALL
                } else if (subqueryOperationPartcontext.INTERSECT() != null) {
                    SetOperator.INTERSECT_DISTINCT
                } else if (subqueryOperationPartcontext.MINUS() != null) {
                    SetOperator.MINUS_DISTINCT
                } else {
                    throw WillNeverHappenException()
                }
                val subTable = subqueryOperationPartcontext.subquery_basic_elements().query_block()?.let { visitQuery_block(it) }
                    ?:subqueryOperationPartcontext.subquery_basic_elements().subquery().let { visitSubQuery(it) }
                setTable.addSetTable(SetOperatorTable.SetTable(setOp, subTable))
            }
            return setTable
        } else {
            return selectTable
        }
    }

    private fun visitQuery_block(ctx: Query_blockContext): SelectTable {
        val selectTable = SelectTable(ctx.getRawText())
        with(selectTable) {
            isDistinct = ctx.DISTINCT()!=null || ctx.UNIQUE() != null
            addFields(visitSelected_list(ctx.selected_list()))
            from = visitFrom_clause(ctx.from_clause())
            where = ctx.where_clause()?.let { visitWhere_clause(it) }
            groupBy = ctx.group_by_clause()?.let { visitGroup_by_clause(it) }
            orderBys = ctx.order_by_clause()?.let { visitOrderByClause(it) }
        }
        if (ctx.into_clause()!=null) {
            if (ctx.into_clause().variable_name().size != selectTable.fields.size) {
                throw IllegalSyntaxException("select into 的字段个数不符")
            }
            ctx.into_clause().variable_name().forEachIndexed { idx, name ->
                run {
                    val varName = name.text
                    if (this.variables[varName] == null) {
                        this.variables[varName] = LinkedList()
                    }
                    this.variables[varName]!!.add(selectTable.fields[idx])
                }
            }
        }
        return selectTable
    }

    private fun visitSelected_list(ctx: Selected_listContext): List<SelectItem> {
        if (ctx.ASTERISK() != null) {
            return listOf(AsteriskField("*").apply { setPosition(ctx) })
        }
        val fields = mutableListOf<SelectItem>()
        for (selectListElement in ctx.select_list_elements()) {
            if (selectListElement.ASTERISK()!=null) {
                fields.add(AsteriskField(selectListElement.getRawText()).apply { setPosition(selectListElement) })
            } else {
                val aliasCtx = selectListElement.column_alias()
                val alias = if (aliasCtx!=null) {
                    var aliasStr = ""
                    val useAs = aliasCtx.AS() !=null
                    when {
                        aliasCtx.identifier()?.also { aliasStr = it.text } != null -> {
                            Alias(aliasStr, useAs).apply { setPos(aliasCtx.identifier().start.startIndex, aliasCtx.identifier().stop.stopIndex) }
                        }
                        aliasCtx.quoted_string()?.also { aliasStr = it.text.trim('"', '\'') } !=null -> {
                            Alias(aliasStr, useAs).apply { setPos(aliasCtx.quoted_string().start.startIndex, aliasCtx.quoted_string().stop.stopIndex) }
                        }
                        else -> {
                            Alias("AS", false).apply { setPos(aliasCtx.start.startIndex, aliasCtx.stop.stopIndex) }
                        }
                    }
                } else Alias.EMPTY
                fields.add(visitExpression(selectListElement.expression()).apply { this.alias = alias })
            }
        }
        return fields
    }

    private fun visitFrom_clause(ctx: From_clauseContext): FromItem {
        val fromTables = ctx.table_ref_list().table_ref().map {visitTable_ref(it)}
        return if (fromTables.size == 1) {
            fromTables[0]
        } else if (fromTables.size > 1) {
            JoinTable(fromTables[0], fromTables.drop(1).map { JoinTable.JoinPart(JoinType.COMMA, it, null) })
        } else {
            EmptyTable()
        }
    }

    private fun visitTable_ref(ctx: Table_refContext): FromItem {

        fun visitDml_table_expression_clause(ctx: Dml_table_expression_clauseContext): FromItem {
            return when {
                ctx.table_collection_expression() != null -> with(ctx.table_collection_expression()){
                    if (subquery() != null) visitSubQuery(subquery())
                    else {
                        val tableExp = ctx.table_collection_expression().getRawText()
                        log.warn("动态表[{}]暂不支持解析", tableExp)
                        EmptyTable(tableExp)
                    }
                }
                ctx.select_statement() != null -> visitSelect_statement(ctx.select_statement())
                else -> PhysicalTable(getTableName(ctx.tableview_name()))
            }
        }

        fun visitTable_ref_aux(ctx: Table_ref_auxContext): FromItem {
            val table = when (val table_ref_aux_internal = ctx.table_ref_aux_internal()) {
                is Table_ref_aux_internal_oneContext -> visitDml_table_expression_clause(table_ref_aux_internal.dml_table_expression_clause())
                is Table_ref_aux_internal_twoContext -> visitTable_ref(table_ref_aux_internal.table_ref())
                is Table_ref_aux_internal_threeContext -> visitDml_table_expression_clause(table_ref_aux_internal.dml_table_expression_clause())
                else -> throw WillNeverHappenException()
            }
            if (table is AliasItem && ctx.table_alias() != null) {
                table.alias = Alias(ctx.table_alias().text, false).apply { setPos(ctx.table_alias().start.startIndex, ctx.table_alias().stop.stopIndex) }
            }
            return table
        }

        val fromTable = visitTable_ref_aux(ctx.table_ref_aux())
        if (ctx.join_clause()?.isNotEmpty() == true) {
            val joinParts = mutableListOf<JoinTable.JoinPart>()
            for (joinClausecontext in ctx.join_clause()) {
                val joinType = when {
                    joinClausecontext.CROSS() != null -> JoinType.CROSS
                    joinClausecontext.NATURAL() != null -> JoinType.NATURAL
                    joinClausecontext.INNER() != null -> JoinType.INNER
                    joinClausecontext.outer_join_type() != null -> {
                        with (joinClausecontext.outer_join_type()) {
                            when {
                                FULL() != null -> JoinType.FULL
                                LEFT() != null -> JoinType.LEFT
                                else -> JoinType.RIGHT
                            }
                        }
                    }
                    else -> JoinType.INNER
                }
                val joinTable = visitTable_ref_aux(joinClausecontext.table_ref_aux())
                val joinOnConditions = joinClausecontext.join_on_part()?.map { visitExpression(it.condition().expression()) }
                val joinPart = JoinTable.JoinPart(joinType, joinTable, joinOnConditions?.firstOrNull())
                joinParts.add(joinPart)
            }
            return JoinTable(fromTable, joinParts)
        } else {
            return fromTable
        }
    }

    private fun visitWhere_clause(ctx: Where_clauseContext): Expression? {
        return if (ctx.cursor_name() !=null) {
            null
        } else {
            visitExpression(ctx.expression())
        }
    }

    private fun visitGroup_by_clause(ctx: Group_by_clauseContext): GroupByExpr {
        val groupByExprs = mutableListOf<SelectItem>()
        fun visitGroupingSetsElements(groupingSetsElement: Grouping_sets_elementsContext): List<SelectItem> {
            if (groupingSetsElement.rollup_cube_clause() != null) {
                return groupingSetsElement.rollup_cube_clause().grouping_sets_elements().flatMap { visitGroupingSetsElements(it) }
            } else if (groupingSetsElement.expressions() != null){
                return groupingSetsElement.expressions().expression().map { visitExpression(it) }
            } else if (groupingSetsElement.expression() != null) {
                return listOf( visitExpression(groupingSetsElement.expression()))
            }
            return emptyList()
        }

        for (groupBy in ctx.group_by_elements()?: emptyList()){
            if (groupBy.expression() != null) {
                groupByExprs.add(visitExpression(groupBy.expression()))
            } else if (groupBy.grouping_sets_clause() != null) {
                for (groupingSetsElement in groupBy.grouping_sets_clause().grouping_sets_elements()) {
                    groupByExprs.addAll(visitGroupingSetsElements(groupingSetsElement))
                }
            } else if (groupBy.rollup_cube_clause() != null) {
                groupByExprs.addAll(groupBy.rollup_cube_clause().grouping_sets_elements().flatMap { visitGroupingSetsElements(it) })
            }
        }
        val having = if (ctx.having_clause() != null) {
            visitExpression(ctx.having_clause().condition().expression())
        } else null
        return GroupByExpr(groupByExprs, having)
    }

    private fun visitOrderByClause(ctx: Order_by_clauseContext): List<OrderByItem> {
        val orderByItems = mutableListOf<OrderByItem>()
        for (orderByElement in ctx.order_by_elements()) {
            val asc = orderByElement.ASC()!=null || orderByElement.DESC() == null
            orderByItems.add(OrderByItem(visitExpression(orderByElement.expression()), asc))
        }
        return orderByItems
    }

    private fun visitExpression(ctx: ExpressionContext): SelectExpr {
        if (ctx.cursor_expression() != null) {
            ctx.cursor_expression().CURSOR()
            return visitSubQuery(ctx.cursor_expression().subquery())
        }
        return visitLogicalExpression(ctx.logical_expression())
    }

    /**
    logical_expression
    : unary_logical_expression
    | logical_expression AND logical_expression
    | logical_expression OR logical_expression
     */
    private fun visitLogicalExpression(ctx: Logical_expressionContext): SelectExpr {
        if (ctx.OR() != null) {
            val left = visitLogicalExpression(ctx.logical_expression(0))
            val right = visitLogicalExpression(ctx.logical_expression(1))
            return BinaryOperatorField(ctx.getRawText()).feed(left, LogicalOperator.OR, right).apply { setPosition(ctx) }
        } else if (ctx.AND() != null) {
            val left = visitLogicalExpression(ctx.logical_expression(0))
            val right = visitLogicalExpression(ctx.logical_expression(1))
            return BinaryOperatorField(ctx.getRawText()).feed(left, LogicalOperator.AND, right).apply { setPosition(ctx) }
        }

        fun visitCompoundExpression(compound: Compound_expressionContext): SelectExpr {
            val concatenation = visitConcatenation(compound.concatenation(0))
            val not = compound.NOT() != null
            return when {
                compound.IN() != null -> {
                    val op = if (not) PredicateOperator.NOT_IN else PredicateOperator.IN
                    val inElement = compound.in_elements()
                    when {
                        inElement.subquery() != null -> MultiOperatorField(compound.getRawText())
                            .feed(concatenation, op, listOf(visitSubQuery(inElement.subquery())))
                        inElement.concatenation()?.isNotEmpty() == true -> MultiOperatorField(compound.getRawText())
                            .feed(concatenation, op, inElement.concatenation().map { visitConcatenation(it) })
                        inElement.constant() != null -> MultiOperatorField(compound.getRawText())
                            .feed(concatenation, op, listOf(ConstantField(inElement.constant().text)))
                        inElement.bind_variable() != null -> MultiOperatorField(compound.getRawText())
                            .feed(concatenation, op, listOf(VariableField(inElement.bind_variable().text)))
                        inElement.general_element() != null -> MultiOperatorField(compound.getRawText())
                            .feed(concatenation, op, listOf(visitGeneralElement(inElement.general_element())))
                        else -> throw WillNeverHappenException()
                    }
                }
                compound.BETWEEN() != null -> {
                    MultiOperatorField(compound.getRawText())
                        .feed(concatenation,
                            if (not) PredicateOperator.NOT_BETWEEN else PredicateOperator.BETWEEN,
                            listOf(visitConcatenation(compound.between_elements().concatenation(0)),
                                visitConcatenation(compound.between_elements().concatenation(1)))
                        )
                }
                compound.like_type != null -> {
                    val op = if (not) PredicateOperator.NOT_LIKE else PredicateOperator.LIKE
                    BinaryOperatorField(compound.getRawText())
                        .feed(concatenation, op, visitConcatenation(compound.concatenation(1)))
                }
                else -> concatenation
            }.apply { setPosition(compound) }
        }

        fun visitRelationalExpression(rel: Relational_expressionContext): SelectExpr {
            return if (rel.compound_expression() != null) {
                visitCompoundExpression(rel.compound_expression())
            } else {
                val left = visitRelationalExpression(rel.relational_expression(0))
                val right = visitRelationalExpression(rel.relational_expression(1))
                val op = OP_MAP[rel.relational_operator().text]
                BinaryOperatorField(rel.getRawText())
                    .feed(left, op, right)
                    .apply { setPosition(rel) }
            }
        }

        val multisetExpr = ctx.unary_logical_expression().multiset_expression()
        var expression = visitRelationalExpression(multisetExpr.relational_expression())
        if (multisetExpr.multiset_type != null) {
            val op = if (multisetExpr.MEMBER() != null) PredicateOperator.MEMBER_OF else PredicateOperator.SUBMULTISET_OF
            expression = BinaryOperatorField(multisetExpr.getRawText()).feed(expression, op, visitConcatenation(multisetExpr.concatenation()))
                .apply { setPosition(multisetExpr) }
        }
        val logicalOperation = ctx.unary_logical_expression().logical_operation()
        if (logicalOperation?.isNotEmpty() == true) {
            for (logicalOperationContext in logicalOperation) {
                var tokenIndex = logicalOperationContext.start.tokenIndex
                var token:Token? = null
                while (tokenIndex == 0 || this.tokenStream.get(--tokenIndex).also { token = it }.channel != 0){}
                val not = token?.type == NOT
                val op = if (not) PredicateOperator.IS_NOT else PredicateOperator.IS
                expression = BinaryOperatorField(this.tokenStream.getText(multisetExpr.start, logicalOperationContext.stop))
                    .feed(expression, op, KeywordField(logicalOperationContext.getRawText()))
                    .apply { setPosition(multisetExpr.start, logicalOperationContext.stop) }
            }
        }
        var multisetStart = multisetExpr.start.tokenIndex
        var notToken: Token? = null
        while ( multisetStart==0 || this.tokenStream.get(--multisetStart).also { notToken = it }.channel != 0) { }
        if (notToken?.type == NOT){
            expression = UnaryOperatorField(ctx.unary_logical_expression().getRawText())
                .feed(expression, LogicalOperator.NOT)
                .apply { setPosition(ctx.unary_logical_expression()) }
        }
        return expression

    }

    /**
     *  concatenation
     *  : model_expression
     *  (AT (LOCAL | TIME ZONE concatenation) | interval_expression)?
     *  | concatenation op=(ASTERISK | SOLIDUS) concatenation
     *  | concatenation op=(PLUS_SIGN | MINUS_SIGN) concatenation
     *  | concatenation BAR BAR concatenation
     *  ;
     *  model_expression
     *  : unary_expression ('[' model_expression_element ']')?
     *  ;
     *  model_expression_element
     *  : (ANY | expression) (',' (ANY | expression))*
     *  | single_column_for_loop (',' single_column_for_loop)*
     *  | multi_column_for_loop
     *  ;
     */
    private fun visitConcatenation(ctx: ConcatenationContext): SelectExpr {
        return if (ctx.op != null) {
            val left = visitConcatenation(ctx.concatenation(0))
            val right = visitConcatenation(ctx.concatenation(1))
            BinaryOperatorField(ctx.getRawText()).feed(left, OP_MAP[ctx.op.text], right).apply { setPosition(ctx) }
        } else if (ctx.BAR()?.isNotEmpty() == true) {
            val left = visitConcatenation(ctx.concatenation(0))
            val right = visitConcatenation(ctx.concatenation(1))
            BinaryOperatorField(ctx.getRawText()).feed(left, CalculateOperator.CONCAT, right).apply { setPosition(ctx) }
        } else {
            val expr = visitUnaryExpression(ctx.model_expression().unary_expression()).apply { setPosition(ctx) }
            if (ctx.model_expression().model_expression_element()!=null){
                //todo
            }

            if (ctx.AT() != null) {
                //todo
            }
            expr
        }
    }

    private fun visitGeneralElementPart(ctx: General_element_partContext): SelectExpr {
        val element = ctx.getRawText(ctx.id_expression(0).start.tokenIndex, ctx.id_expression().last().stop.tokenIndex)
        return if (ctx.function_argument() == null) {
            AtomicField(element).apply { setPosition(ctx) }
        } else {
            val arguments = mutableListOf<Expression>()
            for (argumentContext in ctx.function_argument().argument()) {
                if (argumentContext.identifier() != null) {
                    val identifier = argumentContext.identifier().text
                }
                arguments.add(visitExpression(argumentContext.expression()))
            }
            FunctionField(ctx.getRawText()).feed(element, arguments).apply { setPosition(ctx) }
        }
    }

    private fun visitGeneralElement(ctx: General_elementContext): SelectExpr {
        val parts = ctx.general_element_part().map { visitGeneralElementPart(it) }
        return when {
            parts.size == 1 -> parts[0]
            parts.all { it is AtomicField } -> AtomicField(ctx.getRawText()).apply { setPosition(ctx) }
            else -> CompositeField(ctx.getRawText()).feed(parts).apply { setPosition(ctx) }
        }
    }

    /**
    unary_expression
    : ('-' | '+') unary_expression
    | PRIOR unary_expression
    | CONNECT_BY_ROOT unary_expression
    | /*TODO {input.LT(1).getText().equalsIgnoreCase("new") && !input.LT(2).getText().equals(".")}?*/ NEW unary_expression
    |  DISTINCT unary_expression
    |  ALL unary_expression
    |  /*TODO{(input.LA(1) == CASE || input.LA(2) == CASE)}?*/ case_statement/*[false]*/
    |  quantified_expression
    |  standard_function
    |  atom
     */
    private fun visitUnaryExpression(ctx: Unary_expressionContext): SelectExpr {
        if (ctx.PLUS_SIGN() != null || ctx.MINUS_SIGN() != null) {
            val op = if (ctx.PLUS_SIGN()!= null) CalculateOperator.PLUS else CalculateOperator.MINUS
            return UnaryOperatorField(ctx.getRawText()).feed(visitUnaryExpression(ctx.unary_expression()), op).apply { setPosition(ctx) }
        } else if (ctx.PRIOR() != null) {
            //todo WITH PRIOR()
            return visitUnaryExpression(ctx.unary_expression()).apply { setPosition(ctx) }
        } else if (ctx.CONNECT_BY_ROOT() != null) {
            //todo with CONNECT_BY_ROOT()
            return visitUnaryExpression(ctx.unary_expression()).apply { setPosition(ctx) }
        } else if (ctx.DISTINCT() != null) {
            //todo with DISTINCT()
            return visitUnaryExpression(ctx.unary_expression()).apply { setPosition(ctx) }
        } else if (ctx.ALL() != null) {
            //todo with ALL()
            return visitUnaryExpression(ctx.unary_expression()).apply { setPosition(ctx) }
        } else if (ctx.NEW() != null) {
            //todo with NEW()
            return visitUnaryExpression(ctx.unary_expression()).apply { setPosition(ctx) }
        } else if (ctx.case_statement() != null) {
            with(ctx.case_statement()){
                val caseExpr = simple_case_statement()?.expression()?.let { visitExpression(it) }
                val elseExpr = simple_case_statement()?.case_else_part()?.expression()?.let { visitExpression(it) }
                    ?:searched_case_statement().case_else_part()?.expression()?.let { visitExpression(it) }
                val whenThenExprs:List<CaseField.WhenThen> = (
                        simple_case_statement()?.simple_case_when_part()
                            ?.map { Pair(it.expression(0), if (it.seq_of_statements() == null) it.expression(1) else null) }
                            ?:searched_case_statement().searched_case_when_part()
                                .map { Pair(it.expression(0), if (it.seq_of_statements() == null) it.expression(1) else null) }
                        )
                    .filter { it.second != null }
                    .map { CaseField.WhenThen(visitExpression(it.first), visitExpression(it.second!!)) }
                return CaseField(ctx.case_statement().getRawText()).feed(caseExpr, whenThenExprs, elseExpr).apply { setPosition(ctx) }
            }
        } else if (ctx.quantified_expression() != null) {
            val expr = ctx.quantified_expression().expression()?.let{ visitExpression(it)}
                ?:ctx.quantified_expression().subquery().let { visitSubQuery(it) }
            val quantify = with(ctx.quantified_expression()) { (SOME()?:EXISTS()?:ALL()?:ANY()).text.toUpperCase() }
            return UnaryOperatorField(ctx.getRawText()).feed(expr, OP_MAP[quantify]).apply { setPosition(ctx) }
        } else if (ctx.standard_function() != null) {
            return visitStandardFunction(ctx.standard_function())
        } else if (ctx.atom() != null ) {
            return with(ctx.atom()) {
                when {
                    table_element()!= null ->  AtomicField(table_element().getRawText()).apply { setPosition(table_element()) }
                    bind_variable() != null -> VariableField(bind_variable().text).apply { setPosition(bind_variable()) }
                    constant() != null -> ConstantField(constant().text).apply { setPosition(constant()) }
                    general_element() != null -> visitGeneralElement(general_element())
                    subquery() != null -> visitSubQuery(subquery())
                    expressions() != null -> {
                        val exprs = expressions().expression().map { visitExpression(it) }
                        return if (exprs.size == 1) {
                            exprs[0]
                        } else {
                            CompositeField(expressions().getRawText()).feed(exprs).apply { setPosition(expressions()) }
                        }
                    }
                    else -> throw WillNeverHappenException("")
                }
            }
        }
        throw WillNeverHappenException()
    }

    /**
    standard_function
    : string_function
    | numeric_function_wrapper
    | other_function
    ;

     */
    private fun visitStandardFunction(ctx: Standard_functionContext): FunctionField {
        val functionField = FunctionField(ctx.getRawText()).apply { setPosition(ctx) }
        if (ctx.string_function()!=null){
            /**
            string_function
            : SUBSTR '(' expression ',' expression (',' expression)? ')'
            | TO_CHAR '(' (table_element | standard_function | expression)
            (',' quoted_string)? (',' quoted_string)? ')'
            | DECODE '(' expressions  ')'
            | CHR '(' concatenation USING NCHAR_CS ')'
            | NVL '(' expression ',' expression ')'
            | TRIM '(' ((LEADING | TRAILING | BOTH)? quoted_string? FROM)? concatenation ')'
            | TO_DATE '(' expression (',' quoted_string)? ')'
            ;
             */
            with(ctx.string_function()) {
                when {
                    SUBSTR() != null -> return functionField.feed(SUBSTR().text, expression().map{visitExpression(it)})
                    DECODE() != null -> return functionField.feed(DECODE().text, expressions().expression().map { visitExpression(it) })
                    NVL() != null -> return functionField.feed(NVL().text, expression().map { visitExpression(it) })
                    CHR() != null -> return functionField.feed(CHR().text, listOf( visitConcatenation(concatenation()), KeywordField(USING().text), KeywordField(NCHAR_CS().text)))
                    TO_CHAR() != null -> {
                        val params = mutableListOf<Expression>()
                        table_element()?.also { params.add(AtomicField.of(it.getRawText()).setColumnName(if (it.INTRODUCER()==null) it.text else it.getRawText(it.id_expression(0).start.tokenIndex, it.stop.tokenIndex)).apply { setPosition(it) }) }
                        standard_function()?.also { params.add(visitStandardFunction(it)) }
                        expression()?.firstOrNull { params.add(visitExpression(it)) }
                        quoted_string()?.forEach { params.add(ConstantField(it.text).apply { setPosition(it) }) }
                        return functionField.feed(TO_CHAR().text, params)
                    }
                    TO_DATE() != null -> return functionField.feed(TO_DATE().text, if (quoted_string()?.isNotEmpty() == true) listOf(visitExpression(expression(0))) else listOf(visitExpression(expression(0)), ConstantField(quoted_string(0).text)))
                    TRIM() != null -> {
                        val params = mutableListOf<Expression>()
                        LEADING()?:TRAILING()?:BOTH()?.also { params.add(KeywordField(it.text)) }
                        quoted_string()?.firstOrNull { params.add(ConstantField(it.text).apply { setPosition(it) }) }
                        FROM()?.also { params.add(KeywordField(it.text)) }
                        concatenation().also { params.add(visitConcatenation(it)) }
                        return functionField.feed(TRIM().text, params)
                    }
                    LEFT() != null || RIGHT() != null -> return functionField.feed(LEFT()?.text?:RIGHT()?.text, expressions().expression().map { visitExpression(it) })
                    else -> throw WillNeverHappenException()
                }
            }
        } else if (ctx.numeric_function_wrapper() != null) {
            /**
            numeric_function_wrapper
            : numeric_function (single_column_for_loop | multi_column_for_loop)?
            ;

            numeric_function
            : SUM '(' (DISTINCT | ALL)? expression ')'
            | COUNT '(' ( ASTERISK | ((DISTINCT | UNIQUE | ALL)? concatenation)? ) ')' over_clause?
            | ROUND '(' expression (',' UNSIGNED_INTEGER)?  ')'
            | AVG '(' (DISTINCT | ALL)? expression ')'
            | MAX '(' (DISTINCT | ALL)? expression ')'
            | LEAST '(' expressions ')'
            | GREATEST '(' expressions ')'
            ;
             */
            with(ctx.numeric_function_wrapper().numeric_function()) {
                val keywordField: Expression? = (DISTINCT()?:ALL()?:UNIQUE())?.let {  KeywordField(it.text) }
                when {
                    SUM()!=null -> functionField.feed(SUM().text, if (keywordField!=null) listOf(keywordField, visitExpression(expression())) else listOf(visitExpression(expression())))
                    COUNT()!=null -> functionField.feed(COUNT().text,
                        when {
                            ASTERISK()!=null -> listOf(AsteriskField("*").apply { setPosition(ASTERISK().symbol, ASTERISK().symbol) })
                            keywordField!=null -> listOf(keywordField, visitConcatenation(concatenation()))
                            else -> listOf(visitConcatenation(concatenation()))
                        }
                    )
                    ROUND()!=null -> functionField.feed(ROUND().text, if (keywordField!=null) listOf( visitExpression(expression()), keywordField) else listOf(visitExpression(expression())))
                    AVG()!=null -> functionField.feed(AVG().text, if (keywordField!=null) listOf(keywordField, visitExpression(expression())) else listOf(visitExpression(expression())))
                    MAX()!=null -> functionField.feed(MAX().text, if (keywordField!=null) listOf(keywordField, visitExpression(expression())) else listOf(visitExpression(expression())))
                    LEAST()!=null-> functionField.feed(LEAST().text, expressions().expression().map { visitExpression(it) })
                    GREATEST()!=null-> functionField.feed(GREATEST().text, expressions().expression().map { visitExpression(it) })
                    else -> throw WillNeverHappenException()
                }
                //todo with (single_column_for_loop | multi_column_for_loop)?
            }
        } else if (ctx.other_function() != null) {
            /**
            other_function
            : over_clause_keyword function_argument_analytic over_clause?
            | /*TODO stantard_function_enabling_using*/ regular_id function_argument_modeling using_clause?
            | COUNT '(' ( ASTERISK | (DISTINCT | UNIQUE | ALL)? concatenation) ')' over_clause?
            | (CAST | XMLCAST) '(' (MULTISET '(' subquery ')' | concatenation) AS type_spec ')'
            | COALESCE '(' table_element (',' (numeric | quoted_string))? ')'
            | COLLECT '(' (DISTINCT | UNIQUE)? concatenation collect_order_by_part? ')'
            | within_or_over_clause_keyword function_argument within_or_over_part+
            | cursor_name ( PERCENT_ISOPEN | PERCENT_FOUND | PERCENT_NOTFOUND | PERCENT_ROWCOUNT )
            | DECOMPOSE '(' concatenation (CANONICAL | COMPATIBILITY)? ')'
            | EXTRACT '(' regular_id FROM concatenation ')'
            | (FIRST_VALUE | LAST_VALUE) function_argument_analytic respect_or_ignore_nulls? over_clause
            | standard_prediction_function_keyword
            '(' expressions cost_matrix_clause? using_clause? ')'
            | TRANSLATE '(' expression (USING (CHAR_CS | NCHAR_CS))? (',' expression)* ')'
            | TREAT '(' expression AS REF? type_spec ')'
            | TRIM '(' ((LEADING | TRAILING | BOTH)? quoted_string? FROM)? concatenation ')'
            | XMLAGG '(' expression order_by_clause? ')' ('.' general_element_part)?
            | (XMLCOLATTVAL | XMLFOREST)
            '(' xml_multiuse_expression_element (',' xml_multiuse_expression_element)* ')' ('.' general_element_part)?
            | XMLELEMENT
            '(' (ENTITYESCAPING | NOENTITYESCAPING)? (NAME | EVALNAME)? expression
            (/*TODO{input.LT(2).getText().equalsIgnoreCase("xmlattributes")}?*/ ',' xml_attributes_clause)?
            (',' expression column_alias?)* ')' ('.' general_element_part)?
            | XMLEXISTS '(' expression xml_passing_clause? ')'
            | XMLPARSE '(' (DOCUMENT | CONTENT) concatenation WELLFORMED? ')' ('.' general_element_part)?
            | XMLPI
            '(' (NAME identifier | EVALNAME concatenation) (',' concatenation)? ')' ('.' general_element_part)?
            | XMLQUERY
            '(' concatenation xml_passing_clause? RETURNING CONTENT (NULL_ ON EMPTY)? ')' ('.' general_element_part)?
            | XMLROOT
            '(' concatenation (',' xmlroot_param_version_part)? (',' xmlroot_param_standalone_part)? ')' ('.' general_element_part)?
            | XMLSERIALIZE
            '(' (DOCUMENT | CONTENT) concatenation (AS type_spec)?
            xmlserialize_param_enconding_part? xmlserialize_param_version_part? xmlserialize_param_ident_part? ((HIDE | SHOW) DEFAULTS)? ')'
            ('.' general_element_part)?
            | xmltable
            ;
             */
            with(ctx.other_function()) {
                when {
                    over_clause_keyword()!=null ->
                        functionField.feed(over_clause_keyword().text, function_argument_analytic().argument().map { visitExpression(it.expression()) })    //todo with over_clause
                    regular_id()!=null&&function_argument_modeling()!=null -> {
                        val params = mutableListOf<Expression>()
                        function_argument_modeling().column_name()?.also { params.add(AtomicField(it.text).apply { setPosition(it) }) }
                        expression()?.map { visitExpression(it) }?.also { params.addAll(it) }
                        functionField.feed(regular_id().text, params)  // todo with complecx syntax include useing_clause
                    }
                    COUNT()!=null ->
                        functionField.feed(COUNT().text, (ASTERISK()?:DISTINCT()?:UNIQUE()?:ALL())?.let { KeywordField(it.text) }, visitConcatenation(concatenation(0))) //todo with over_clause
                    CAST()!=null || XMLCAST()!=null ->
                        functionField.feed((CAST()?:XMLCAST()).text, subquery()?.let { visitSubQuery(it) }, concatenation(0)?.let { visitConcatenation(it) }, KeywordField(AS().text), KeywordField(type_spec().getRawText()))
                    COALESCE()!=null ->
                        functionField.feed(COALESCE().text, AtomicField(table_element().getRawText()).apply { setPosition(table_element()) }, (numeric()?:quoted_string())?.let { ConstantField(it.text) })
                    COLLECT()!=null ->
                        functionField.feed(COLLECT().text, (DISTINCT()?:UNIQUE())?.let { KeywordField(it.text) }, visitConcatenation(concatenation(0)), /** todo with collect_order_by_part */)
                    within_or_over_clause_keyword() != null ->
                        functionField.feed(within_or_over_clause_keyword().text, function_argument().argument().map { visitExpression(it.expression()) }, /** todo with within_or_over_part */)
                    cursor_name() !=null ->
                        functionField.feed(cursor_name().text, KeywordField ((PERCENT_ISOPEN()?:PERCENT_FOUND()?:PERCENT_NOTFOUND()?:PERCENT_ROWCOUNT()).text))
                    DECOMPOSE()!=null ->
                        functionField.feed(DECOMPOSE().text, visitConcatenation(concatenation(0)), (CANONICAL()?:COMPATIBILITY())?.let { KeywordField(it.text) })
                    EXTRACT()!=null ->
                        functionField.feed(EXTRACT().text, AtomicField.of(regular_id().text).apply { setPosition(regular_id()) }, KeywordField(FROM().text), visitConcatenation(concatenation(0)) )
                    FIRST_VALUE()!=null || LAST_VALUE()!=null ->
                        functionField.feed((FIRST_VALUE()?:LAST_VALUE()).text, function_argument_analytic().argument().map { visitExpression(it.expression()) }, )
                    standard_prediction_function_keyword() != null ->
                        functionField.feed(standard_prediction_function_keyword().text, expressions().expression().map { visitExpression(it) })
                    TRANSLATE()!=null ->
                        functionField.feed(TRANSLATE().text, listOf<Expression?>( visitExpression(expression(0)), USING()?.run { KeywordField(text) }, (CHAR_CS()?:NCHAR_CS())?.run { KeywordField(text) }).toMutableList().apply { addAll( expression().drop(1).map { visitExpression(it) }) } )
                    TREAT()!=null ->
                        functionField.feed(TREAT().text, listOf(visitExpression(expression(0)), KeywordField(AS().text), REF()?.run { KeywordField(text) }, ConstantField(type_spec().getRawText())))
                    TRIM()!=null ->
                        functionField.feed(TRIM().text, listOf( (LEADING()?:TRAILING()?:BOTH())?.run { KeywordField(text) }, quoted_string()?.run { ConstantField(text) }, FROM()?.run { KeywordField(text) }, visitConcatenation(concatenation(0))))
                    XMLAGG() != null ->
                        functionField.feed(XMLAGG().text, visitExpression(expression(0)))
                    XMLCOLATTVAL()!=null || XMLFOREST()!=null ->
                        functionField.feed((XMLCOLATTVAL()?:XMLFOREST()).text, xml_multiuse_expression_element().map { if (it.AS()!=null||it.EVALNAME()!=null) CompositeField(it.getRawText()).feed(visitExpression(it.expression()), KeywordField((it.AS()?:it.EVALNAME()).text), it.id_expression()?.run { ConstantField(text) }?: visitConcatenation(
                            it.concatenation()
                        )) else visitExpression(it.expression()) })
                    XMLELEMENT() != null -> functionField.feed(XMLELEMENT().text, expression().map { visitExpression(it) }) //todo
                    XMLEXISTS() != null -> functionField.feed(XMLEXISTS().text, visitExpression(expression(0))) //todo xml_passing_clause
                    XMLPARSE() != null -> functionField.feed(XMLPARSE().text, visitConcatenation(concatenation(0)))
                    XMLPI() != null -> functionField.feed(XMLPI().text, identifier()?.let { AtomicField(it.text).apply { setPosition(it) } }?:visitConcatenation(concatenation(0))) //todo
                    XMLQUERY() != null -> functionField.feed(XMLQUERY().text, visitConcatenation(concatenation(0))) // todo with xml_passing_clause
                    XMLROOT() != null -> functionField.feed(XMLROOT().text, visitConcatenation(concatenation(0)))
                    XMLSERIALIZE() != null -> functionField.feed(XMLSERIALIZE().text, visitConcatenation(concatenation(0))) //todo
                    xmltable() != null -> functionField.feed(xmltable().XMLTABLE().text, visitConcatenation(xmltable().concatenation())) //todo
                    else -> throw WillNeverHappenException("")
                }
            }
        }
        return functionField
    }

    companion object {
        private val log = LoggerFactory.getLogger(OracleSqlParseListener::class.java)

        @JvmStatic
        val OP_MAP:Map<String, Operator> = mapOf(
            "*" to CalculateOperator.MUL,
            "/" to CalculateOperator.DIV,
            "MOD" to CalculateOperator.MOD,
            "%" to CalculateOperator.MOD,

            "+" to CalculateOperator.PLUS,
            "-" to CalculateOperator.MINUS,

            "=" to PredicateOperator.NOT_EQUAL,
            "!=" to PredicateOperator.NOT_EQUAL,
            "<>" to PredicateOperator.NOT_EQUAL,
            "^=" to PredicateOperator.NOT_EQUAL,
            "~=" to PredicateOperator.NOT_EQUAL,

            "<=" to PredicateOperator.LESS_EQUAL,
            "<" to PredicateOperator.LESS,
            ">" to PredicateOperator.GREATER,
            ">=" to PredicateOperator.GREATER_EQUAL,

            "LIKE" to PredicateOperator.LIKE,

            "AND" to LogicalOperator.AND,
            "OR" to LogicalOperator.OR,
            "NOT" to LogicalOperator.NOT,

            "&" to BitOperator.AND,
            "~" to BitOperator.TILDE,
            "|" to BitOperator.OR,
            "^" to BitOperator.XOR,

            "||" to CalculateOperator.CONCAT,

            "EXISTS" to PredicateOperator.EXISTS,
            "SOME" to PredicateOperator.SOME,
            "ALL" to PredicateOperator.ALL,
            "ANY" to PredicateOperator.ANY,

            )
    }
}