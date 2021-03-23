package org.sunt.sqlanalysis.parser.hive

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.TokenStream
import org.slf4j.LoggerFactory
import org.sunt.sqlanalysis.exception.IllegalSyntaxException
import org.sunt.sqlanalysis.exception.WillNeverHappenException
import org.sunt.sqlanalysis.model.*
import org.sunt.sqlanalysis.parser.SqlParseListener
import org.sunt.sqlanalysis.parser.hive.grammar.HiveParser.*
import org.sunt.sqlanalysis.parser.hive.grammar.HiveParserBaseListener
import java.util.*
import kotlin.collections.ArrayList

/** @see <a href="https://github.com/JetBrains/kotlin/blob/master/compiler/frontend/src/org/jetbrains/kotlin/diagnostics/rendering/DefaultErrorMessages.java" */
@Suppress("UNUSED_VALUE", "UNUSED_EXPRESSION", "UNUSED_VARIABLE")
internal class HiveSqlParseListener(override val tokenStream: TokenStream) : HiveParserBaseListener(), SqlParseListener {

    override val tables: MutableList<Table> = mutableListOf()

    private var withTables: Map<String, SelectTable> = emptyMap()

    private var windowFunctions: Map<String, WindowField> = emptyMap()

    private fun getTableName(ctx: TableNameContext): String {
        return ctx.identifier().map { it.text.trim('`') }.joinToString(".")
    }

    private fun getAlias(ctx: IdentifierContext, useAs: Boolean = true): Alias {
        return Alias(ctx.getRawText().trim('`'), useAs).setPos(ctx.start.startIndex, ctx.stop.stopIndex)
    }

    override fun exitDeleteStatement(ctx: DeleteStatementContext) {
        val tableName = getTableName(ctx.tableName())
        val where =  ctx.whereClause()?.searchCondition()?.expression()?.let { visitExpression(it) }
        val deleteTable = DeleteTable(tableName).setWhere(where)
        tables.add(deleteTable)
    }

    override fun exitUpdateStatement(ctx: UpdateStatementContext) {
        val tableName = getTableName(ctx.tableName())
        val where = ctx.whereClause()?.searchCondition()?.expression()?.let { visitExpression(it) }
        val updateTable = UpdateTable(tableName).setWhere(where)
        tables.add(updateTable)
    }

    override fun exitDropTableStatement(ctx: DropTableStatementContext) {
        this.tables.add(DropTable(getTableName(ctx.tableName())).setIfExists(ctx.ifExists()!=null))
    }

    override fun exitCreateTableStatement(ctx: CreateTableStatementContext) {
        val tableName = getTableName(ctx.tableName(0))
        //val temporary = ctx.KW_TEMPORARY() != null
        //val external = ctx.KW_EXTERNAL() != null
        val createTable = CreateTable(tableName)
        if (ctx.KW_LIKE() != null) {
            val likeTable = PhysicalTable(getTableName(ctx.tableName(1))).addField(AsteriskField("*").setPhysical(true))
            createTable.setSourceTable(likeTable).addField(AsteriskField("*"))
        } else {
            val colNameTypeConstraintList = mutableListOf<ColumnNameTypeConstraintContext>()
            if (ctx.columnNameTypeOrConstraintList() != null) {
                ctx.columnNameTypeOrConstraintList().columnNameTypeOrConstraint()
                        .mapNotNull { it.columnNameTypeConstraint() }
                        .forEach{ colNameTypeConstraintList.add(it) }
            }
            val partitionFieldIndex = colNameTypeConstraintList.size
            if (ctx.tablePartition()?.columnNameTypeConstraint()?.isNotEmpty() == true) {
                colNameTypeConstraintList.addAll(ctx.tablePartition().columnNameTypeConstraint())
            }
            for ((fieldIndex, colDefConst) in colNameTypeConstraintList.withIndex()) {
                val columnName = colDefConst.identifier().getRawText()
                val dataType = visitColType(colDefConst.colType())
                val columnComment = colDefConst.StringLiteral()?.text?.trim('\'', '"')
                val createField = CreateField(colDefConst.getRawText()).apply {
                    comment = columnComment
                    setDataType(dataType)
                    isPartitionField = fieldIndex >= partitionFieldIndex
                    setColumnName(columnName)
                    setPosition(colDefConst.identifier())
                }
                createTable.addField(createField)
//                        val constraint: ColumnConstraintContext
//                        if ( colDefConst.columnConstraint().also { constraint = it } != null ) {
//                            if (constraint.foreignKeyConstraint() != null) {
//
//                            } else if (constraint.colConstraint() != null){
//
//                            } else {
//                                throw ImpossibleException()
//                            }
//                        }
            }

            if (ctx.selectStatementWithCTE() != null) {
                val asSelectTable = visitSelectStatementWithCTE(ctx.selectStatementWithCTE())
                createTable.setSourceTable(asSelectTable)
                if (createTable.fields.isEmpty()) {
                    createTable.addField(asSelectTable.fields.map { AtomicField.of(it.label) })
                }
            }
            createTable.comment = ctx.tableComment()?.StringLiteral()?.text?.trim('\'', '"')
        }
        this.tables.add(createTable)
    }

    override fun exitCreateViewStatement(ctx: CreateViewStatementContext) {
        val tableName = getTableName(ctx.tableName())
        val tableComment = ctx.tableComment()?.StringLiteral()?.text?.trim('\'', '"')
        val viewTable = CreateTable(tableName)
                .setComment(tableComment)
                .setView(true)
        val asSelectTable = visitSelectStatementWithCTE(ctx.selectStatementWithCTE())
        if (ctx.columnNameCommentList() != null) {
            for (columnNameCommentContext in ctx.columnNameCommentList().columnNameComment()) {
                val viewField = CreateField(columnNameCommentContext.getRawText())
                        .setComment(columnNameCommentContext.StringLiteral()?.text?.trim('\'', '"'))
                        .setColumnName(columnNameCommentContext.identifier().getRawText())
                        .apply {
                            setPosition(columnNameCommentContext.identifier())
                        }
                viewTable.addField(viewField)
            }
        } else {
            viewTable.addField(asSelectTable.fields.map { AtomicField.of(it.label) })
        }
        viewTable.setSourceTable(asSelectTable)
        this.tables.add(viewTable)
    }

    override fun exitCreateMaterializedViewStatement(ctx: CreateMaterializedViewStatementContext) {
        val tableName = getTableName(ctx.tableName())
        val tableComment = ctx.tableComment()?.StringLiteral()?.text?.trim('\'', '"')
        val viewTable = CreateTable(tableName)
                .setComment(tableComment)
                .setView(true)
                .setMaterialized(true)
        val asSelectTable = visitSelectStatementWithCTE(ctx.selectStatementWithCTE())
        viewTable.setSourceTable(asSelectTable).addField(asSelectTable.fields.map { AtomicField.of(it.label) })
        this.tables.add(viewTable)
    }

    override fun exitQueryStatementExpression(ctx: QueryStatementExpressionContext) {
        if (ctx.parent !is ExecStatementContext) return
        val queryTable = visitQueryStatementExpression(ctx)
        this.tables.add(queryTable)
    }

    override fun exitSearchCondition(ctx: SearchConditionContext) {
//        if (ctx.parent is WhereClauseContext) {
//            val where = visitExpression(ctx.expression())
//        }
    }

    private fun visitQueryStatementExpression(ctx: QueryStatementExpressionContext): Table {
        val originalWithTables = this.withTables
        try {
            this.withTables = ctx.withClause()?.let { visitWithClause(it) }?:originalWithTables
            var subCtx: ParserRuleContext? = null
            when {
                ctx.queryStatementExpressionBody().fromStatement()?.also { subCtx = it } != null -> {
                    /*
                    Hive extension (multiple inserts):
                    FROM from_statement
                    INSERT OVERWRITE TABLE tablename1 [PARTITION (partcol1=val1, partcol2=val2 ...) [IF NOT EXISTS]] select_statement1
                    [INSERT OVERWRITE TABLE tablename2 [PARTITION ... [IF NOT EXISTS]] select_statement2]
                    [INSERT INTO TABLE tablename2 [PARTITION ...] select_statement2] ...;
                    */
                    @Suppress("NAME_SHADOWING")
                    val subCtx = subCtx as FromStatementContext
                    val setTables = mutableListOf<Table>()
                    var isSelect: Boolean? = null
                    for (i in 0 until subCtx.singleFromStatement().size) {
                        if (i > 0 && isSelect == false) {
                            throw IllegalSyntaxException("from 查询不支持多个Insert组合")
                        }
                        val singleFrom: SingleFromStatementContext = subCtx.singleFromStatement(i)
                        val fromTable = visitFromClause(singleFrom.fromClause())
                        val tableList = mutableListOf<Table>()
                        for (bodyContext in singleFrom.body()) {
                            val table = visitBody(bodyContext, fromTable)
                            if (isSelect == null) {
                                isSelect = table is SelectTable
                            } else if ((isSelect && table !is SelectTable) || (!isSelect && table is SelectTable)) {
                                throw IllegalSyntaxException("from 查询不支持Insert和Select查询混用")
                            }
                            tableList.add(table)
                        }
                        if (tableList.size == 1) {
                            //单个Insert语句或者单个Select
                            setTables.add(tableList[0])
                        } else if (isSelect!!) {
                            throw IllegalSyntaxException("from 查询不支持多个select查询")
                        } else {
                            //多个Insert语句
                            val filteredList = tableList.filterIsInstance<InsertTable>()
                            if (filteredList.size == 1) {
                                return filteredList[0]
                            }
                            return MultiInsertTable(fromTable, filteredList)
                        }
                    }
                    if (setTables.size == 1) {
                        return setTables[0]
                    } else if (setTables.size > 1) {
                        if (isSelect == true) {
                            //多个select 集合
                            val setOps = subCtx.setOperator().map { SetOperator.of(it.text) }
                            if (setTables.size != setOps.size + 1) {
                                throw WillNeverHappenException("应该不会发生，不然语法解析会报错")
                            }
                            val setTable = SetOperatorTable(ctx.getRawText())
                            setTable.firstTable = setTables[0] as SelectTable
                            for (i in setOps.indices) {
                                setTable.addSetTable(SetOperatorTable.SetTable(setOps[i], setTables[i + 1] as SelectTable))
                            }
                            return setTable
                        }
                    }
                    return EmptyTable()
                }
                ctx.queryStatementExpressionBody().regularBody()?.insertClause()?.also { subCtx = it } != null -> {
                    if (ctx.queryStatementExpressionBody().regularBody().valuesClause() != null) {
                        log.info("忽略解析insert ... values 语句")
                        return EmptyTable()
                    }
                    val insertTable = visitInsertClause(subCtx as InsertClauseContext)
                    if (insertTable is InsertTable) {
                        val selectTable = visitSelectStatement(ctx.queryStatementExpressionBody().regularBody().selectStatement())
                        insertTable.setSourceTable(selectTable)
                        if (insertTable.fields.isEmpty()) {
                            insertTable.addFields(selectTable.fields.map { AtomicField.of(it.label) })
                        }
                    }
                    return insertTable
                }
                else -> {
                    return visitSelectStatement(ctx.queryStatementExpressionBody().regularBody().selectStatement())
                }
            }
        } finally {
            this.withTables = originalWithTables
        }
    }

    private fun visitSelectStatementWithCTE(ctx: SelectStatementWithCTEContext): SelectTable {
        val originalWithTables = this.withTables
        try {
            this.withTables = ctx.withClause()?.let { visitWithClause(it) }?: originalWithTables
            return visitSelectStatement(ctx.selectStatement())
        } finally {
            this.withTables = originalWithTables
        }
    }

    private fun visitWithClause(ctx: WithClauseContext?): Map<String, SelectTable> {
        if (ctx == null) return emptyMap()
        val originalWithTables = this.withTables;
        val withTables = TreeMap<String, SelectTable>(String.CASE_INSENSITIVE_ORDER)
        this.withTables = withTables
        //with中包含with的引用，及先使用，后定义的情形， 先正序遍历一遍，再逆序遍历一遍
        //e.g.  with q1 as ( select key from q2 where key = '5'),
        //      q2 as ( select key from src where key = '5')
        //      select * from (select key from q1) a;
        // use cte q2 before define it
        val size = ctx.cteStatement().size
        for (i in 0 until size*2-1) {
            val cteStatementContext = ctx.cteStatement(if (i<size) i else 2*(size-1)-i)
            withTables[cteStatementContext.identifier().getRawText().trim('`')] = visitQueryStatementExpression(cteStatementContext.queryStatementExpression()) as SelectTable
        }
        this.withTables = originalWithTables
        return withTables
    }

    private fun visitInsertClause(ctx: InsertClauseContext): Table {
        if (ctx.destination() != null) {
            if (ctx.destination().KW_DIRECTORY() != null) {
                log.warn("忽略解析 insert overwrite directory 语句")
                return EmptyTable(ctx.getRawText())
            }
            val tableName = ctx.destination().tableOrPartition().tableName().getRawText()
            return InsertTable(tableName).setOverwrite(ctx.KW_OVERWRITE()!=null)
        } else {
            val tableName = ctx.tableOrPartition().tableName().getRawText()
            val insertTable = InsertTable(tableName).setOverwrite(ctx.KW_OVERWRITE()!=null).setRealFields(ctx.columnNameList()?.columnName()?.isNotEmpty()==true)
            ctx.columnNameList()
                    ?.columnName()
                    ?.map { AtomicField(it.getRawText().trim('`')).apply { setPosition(it) } }
                    ?.forEach { insertTable.addFields(it) }

            val partitionFields = mutableListOf<SelectExpr>()
            for (partitionVal in (ctx.partitionSpec()?:ctx.tableOrPartition().partitionSpec())?.partitionVal()?: emptyList()) {
                val fieldName = partitionVal.identifier().getRawText().trim('`')
                val field = AtomicField(fieldName).apply { setPosition(partitionVal.identifier()); isPartition = true }
                val fieldValue = partitionVal.expression()?.let { visitExpression(it) }
                if (fieldValue == null) {
                    insertTable.addFields(field)
                    partitionFields.add(field)
                } else {
                    val assignField = BinaryOperatorField(partitionVal.getRawText())
                            .feed(field, PredicateOperator.EQUAL, fieldValue)
                            .apply { setPosition(partitionVal) }
                    partitionFields.add(assignField)
                }
            }
            insertTable.partitionFields = partitionFields
            return insertTable
        }
    }

    private fun visitSelectStatement(ctx: SelectStatementContext): SelectTable {
        val table = visitAtomSelectStatement(ctx.atomSelectStatement())
        return if (ctx.setOpSelectStatement() != null) {
            val setTable = SetOperatorTable(ctx.getRawText()).setFirstTable(table)
            val opSel = ctx.setOpSelectStatement()
            var fillFields = false
            for (i in 0 until opSel.atomSelectStatement().size) {
                val setOp = SetOperator.of(opSel.setOperator(i).getRawText())
                val select = visitAtomSelectStatement(opSel.atomSelectStatement(i))
                if (!fillFields && select.fields.none { it is AsteriskField }) {
                    setTable.addFields(select.fields.map { AtomicField.of(it.label) })
                    fillFields = true
                }
                setTable.addSetTable(SetOperatorTable.SetTable(setOp, select))
            }
            if (!fillFields) {
                setTable.addFields(table.fields.map { AtomicField.of(it.label) })
            }
            setTable
        } else{
            table
        }
    }

    private fun visitAtomSelectStatement(ctx: AtomSelectStatementContext): SelectTable {
        val originWindowFunctions = this.windowFunctions
        this.windowFunctions = ctx.window_clause()?.let { visitWindowClause(it) }?: emptyMap()
        try {
            val selectTable = SelectTable(ctx.getRawText())
            with(selectTable) {
                isDistinct = ctx.selectClause().KW_DISTINCT() != null
                addFields(visitSelectClause(ctx.selectClause()))
                from = ctx.fromClause()?.let { visitFromClause(it) }
                where = ctx.whereClause()?.let { visitWhereClause(it) }
                groupBy = ctx.groupByClause()?.let { visitGroupByClause(it, ctx.havingClause()) }
                orderBys = ctx.orderByClause()?.let { visitOrderByClause(it) }
            }
            return selectTable
        } finally {
            this.windowFunctions = originWindowFunctions
        }
    }

    private fun visitBody(ctx: BodyContext, table: FromItem): Table {
        val originWindowFunctions = this.windowFunctions
        this.windowFunctions = ctx.window_clause()?.let { visitWindowClause(it) }?: emptyMap()
        val selectTable = SelectTable(ctx.getRawText())
        with(selectTable) {
            isDistinct = ctx.selectClause().KW_DISTINCT() != null
            addFields(visitSelectClause(ctx.selectClause()))
            from = table
            where = ctx.whereClause()?.let { visitWhereClause(it) }
            groupBy = ctx.groupByClause()?.let { visitGroupByClause(it, ctx.havingClause()) }
            orderBys = ctx.orderByClause()?.let { visitOrderByClause(it) }
        }
        try {
            if (ctx.insertClause() != null) {
                val insertTable = visitInsertClause(ctx.insertClause())
                if (insertTable is InsertTable) {
                    insertTable.setSourceTable(selectTable)
                    if (insertTable.fields.isEmpty()) {
                        insertTable.addFields(selectTable.fields.map { AtomicField.of(it.label) })
                    }
                }
                return insertTable
            }
            return selectTable
        } finally {
            this.windowFunctions = originWindowFunctions
        }

    }

    private fun visitSelectClause(ctx: SelectClauseContext): List<SelectItem> {
        if (ctx.trfmClause() != null || ctx.selectTrfmClause() != null) {
            log.warn("Transform Or Custom Map/Reduce Syntax Not Supported Yet")
            var fields: List<SelectExpr> = emptyList()
            if (ctx.trfmClause() != null) {
                fields = ctx.trfmClause().selectExpressionList().selectExpression().map { visitSelectExpression(it) }
                ctx.trfmClause().aliasList()?.identifier()?.mapIndexed { index, identifierContext -> fields[index].alias = getAlias(identifierContext) }
                ctx.trfmClause().columnNameTypeList()?.columnNameType()?.mapIndexed { index, columnNameTypeContext -> fields[index].alias = getAlias(columnNameTypeContext.identifier()) }
            } else if (ctx.selectTrfmClause() != null) {
                fields = ctx.selectTrfmClause().selectExpressionList().selectExpression().map { visitSelectExpression(it) }
                ctx.selectTrfmClause().aliasList()?.identifier()?.mapIndexed { index, identifierContext -> fields[index].alias = getAlias(identifierContext) }
                ctx.selectTrfmClause().columnNameTypeList()?.columnNameType()?.mapIndexed { index, columnNameTypeContext -> fields[index].alias = getAlias(columnNameTypeContext.identifier()) }
            }
            return fields
        }
        //val all = ctx.KW_ALL() != null
        //val distinct = ctx.KW_DISTINCT() != null
        return ctx.selectList().selectItem().map { visitSelectItem(it) }
    }

    private fun visitFromClause(ctx: FromClauseContext): FromItem {
        val fromSource = ctx.fromSource()
        return if (fromSource.uniqueJoinSource()?.isNotEmpty()==true) {
            log.warn("UniqueJoin Syntax Not Supported Yet")
            EmptyTable(ctx.getRawText())
        } else {
            visitJoinSource(fromSource.joinSource())
        }
    }

    private fun visitWhereClause(ctx: WhereClauseContext): Expression {
        return visitExpression(ctx.searchCondition().expression())
    }

    private fun visitGroupByClause(groupByClause: GroupByClauseContext, having: HavingClauseContext?): GroupByExpr {
        val groupBy = groupByClause.groupby_expression()
        val groupByFields: List<SelectItem>;
        if (groupBy.rollupStandard() != null) {
            val rollup = groupBy.rollupStandard().KW_ROLLUP()!=null
            val cube = groupBy.rollupStandard().KW_CUBE() != null
            groupByFields = groupBy.rollupStandard().expression().map { visitExpression(it) }

        } else if (groupBy.rollupOldSyntax() != null) {
            groupByFields = mutableListOf(visitExpression(groupBy.rollupOldSyntax().expressionsNotInParenthesis().expression()))
            groupByFields.addAll(groupBy.rollupOldSyntax().expressionsNotInParenthesis().expressionPart()?.expression()?.map { visitExpression(it) }.orEmpty())
            val rollUp = groupBy.rollupOldSyntax().KW_ROLLUP() != null
            val cube = groupBy.rollupOldSyntax().KW_CUBE() != null
            val groupingSetFields = groupBy.rollupOldSyntax().groupingSetExpression()
                    ?.flatMap {
                        if (it.groupingExpressionSingle() != null) {
                            listOf(visitExpression(it.groupingExpressionSingle().expression()))
                        } else {
                            it.groupingSetExpressionMultiple()?.expression()?.map { e-> visitExpression(e) }.orEmpty()
                        }
                    }.orEmpty()
        } else {
            groupByFields = emptyList()
        }
        val havingExpr = having?.havingCondition()?.expression()?.let { visitExpression(it) }
        return GroupByExpr(groupByFields, havingExpr)
    }

    private fun visitWindowClause(ctx: Window_clauseContext): Map<String, WindowField> {
        val windowDefMap = TreeMap<String, WindowField>(String.CASE_INSENSITIVE_ORDER)
        for (windowDefncontext in ctx.window_defn()) {
            windowDefMap[windowDefncontext.identifier().getRawText().trim('`')] = visitWindowSpecification(windowDefncontext.window_specification())
        }
        return windowDefMap
    }

    private fun visitWindowSpecification(ctx: Window_specificationContext): WindowField {
        if (ctx.identifier()!=null) {
            return this.windowFunctions[ctx.identifier().getRawText().trim('`')]?:throw IllegalSyntaxException("未找到的OVER 字句定义${ctx.identifier().getRawText()}")
        }
        val windowField = WindowField((ctx.parent as ParserRuleContext).getRawText())
        if (ctx.partitioningSpec() != null) {
            with(ctx.partitioningSpec()) {
                val partitionByItems = mutableListOf<SelectExpr>()
                if (partitionByClause() != null) {
                    val expressionNotInParenthesis = partitionByClause().expressions().expressionsNotInParenthesis()?:partitionByClause().expressions().expressionsInParenthesis().expressionsNotInParenthesis()
                    partitionByItems.add(visitExpression(expressionNotInParenthesis.expression()))
                    partitionByItems.addAll(expressionNotInParenthesis.expressionPart()?.expression()?.map { visitExpression(it) }?: emptyList())
                }
                val orderByItems = orderByClause()?.columnRefOrder()?.map { OrderByItem(visitExpression(it.expression()), it.orderSpecification()?.text?.equals("ASC", true)?:true) }?: emptyList()
                windowField.feed(partitionByItems.toList(), orderByItems)
            }
        }
        if (ctx.window_frame() != null) {
            val windowFrame = with(ctx.window_frame()) {
                val unit = window_range_expression()?.let { WindowField.WindowFrameUnit.ROWS }?:WindowField.WindowFrameUnit.RANGE
                val start = window_range_expression()?.window_frame_start_boundary()?:window_value_expression()?.window_frame_start_boundary()
                if (start != null) {
                    val winFrame = if (start.KW_UNBOUNDED() != null)
                        WindowField.WindowFrame.WindowBound.UNBOUNDED_PRECEDING
                    else if (start.KW_CURRENT() !=null) {
                        WindowField.WindowFrame.WindowBound.CURRENT_ROW
                    } else {
                        WindowField.WindowFrame.WindowBound.preceding(start.Number().text.toInt())
                    }
                    WindowField.WindowFrame(unit, winFrame)
                } else {
                    val boundary = window_range_expression()?.window_frame_boundary()?:window_value_expression().window_frame_boundary()
                    WindowField.WindowFrame(unit, visitWindowFrameBoundary(boundary[0]), visitWindowFrameBoundary(boundary[1]))
                }
            }
            windowField.feed(windowFrame)
        }
        return windowField
    }

    private fun visitWindowFrameBoundary(ctx: Window_frame_boundaryContext): WindowField.WindowFrame.WindowBound{
        val direction = if ( ctx.KW_PRECEDING() != null) WindowField.Direction.PRECEDING else WindowField.Direction.FOLLOWING
        return if (ctx.KW_UNBOUNDED() != null) {
            if (direction==WindowField.Direction.PRECEDING)
                WindowField.WindowFrame.WindowBound.UNBOUNDED_PRECEDING
            else
                WindowField.WindowFrame.WindowBound.UNBOUNDED_FOLLOWING
        } else if (ctx.KW_CURRENT() != null) {
            WindowField.WindowFrame.WindowBound.CURRENT_ROW
        } else {
            if (direction == WindowField.Direction.PRECEDING)
                WindowField.WindowFrame.WindowBound.preceding(ctx.Number().text.toInt())
            else
                WindowField.WindowFrame.WindowBound.following(ctx.Number().text.toInt())
        }
    }

    private fun visitOrderByClause(ctx: OrderByClauseContext): List<OrderByItem> {
        return ctx.columnRefOrder()
                .map {
                    OrderByItem(visitExpression(it.expression()), it.orderSpecification()?.text?.equals("ASC", true)?:true)
                }
    }

    private fun visitSelectItem(ctx: SelectItemContext): SelectItem {
        return if (ctx.tableAllColumns() != null) {
            AsteriskField(ctx.getRawText()).apply { setPosition(ctx) }
        } else {
            val expression = visitExpression(ctx.expression()).apply { setPosition(ctx) }
            val aliases = ArrayList<Alias>(ctx.identifier()?.size?:0)
            for (id in ctx.identifier()?: emptyList()) {
                aliases.add(getAlias(id, ctx.KW_AS() != null))
            }
            if (aliases.size>0) {
                expression.alias = aliases[0]
            }
            expression
        }
    }

    private fun visitSelectExpression(ctx: SelectExpressionContext): SelectExpr {
        return if (ctx.tableAllColumns() != null) {
            AsteriskField(ctx.getRawText()).apply { setPosition(ctx) }
        } else {
            visitExpression(ctx.expression()).apply { setPosition(ctx) }
        }
    }

    private fun visitExpression(ctx: ExpressionContext): SelectExpr {
        when(ctx) {
            is PrecedenceFieldExpressionContext -> {
                val expr = visitAtomExpression(ctx.atomExpression())
                //以下用于访问列表或者对象类型数据
                if (ctx.expression() != null) {
                //A[n]: A is an Array and n is an int
                //M[key]: M is a Map<K, V> and key has type K

                } else if (ctx.identifier() != null) {
                //S.x: S is a struct

                }
                return expr
            }
            is PrecedenceUnaryPrefixExpressionContext -> {
                ctx.precedenceUnaryOperator()
                val right = visitExpression(ctx.expression())
                val operator = OP_MAP[ctx.precedenceUnaryOperator().start.type]
                return UnaryOperatorField(ctx.getRawText()).feed(right, operator)
            }
            is PrecedenceUnarySuffixExpressionContext -> {
                val left = visitExpression(ctx.expression())
                val right = if (ctx.isCondition.KW_NULL() != null){
                    ConstantField("NULL")
                } else if (ctx.isCondition.KW_TRUE() != null) {
                    ConstantField("TRUE")
                } else if (ctx.isCondition.KW_FALSE() != null) {
                    ConstantField("FALSE")
                } else {
                    throw WillNeverHappenException()
                }
                val operator = if (ctx.isCondition.KW_NOT() != null) PredicateOperator.IS_NOT else PredicateOperator.IS
                return BinaryOperatorField(ctx.getRawText()).feed(left, operator, right)
            }
            is PrecedenceBitwiseXorExpressionContext -> {
                val left = visitExpression(ctx.expression(0))
                val right = visitExpression(ctx.expression(1))
                val operator = OP_MAP[ctx.precedenceBitwiseXorOperator().start.type]
                return BinaryOperatorField(ctx.getRawText()).feed(left, operator, right)
            }
            is PrecedenceStarExpressionContext -> {
                //乘除模余
                val left = visitExpression(ctx.expression(0))
                val right = visitExpression(ctx.expression(1))
                val operator = OP_MAP[ctx.precedenceStarOperator().start.type]
                return BinaryOperatorField(ctx.getRawText()).feed(left, operator, right)
            }
            is PrecedencePlusExpressionContext -> {
                //加减
                val left = visitExpression(ctx.expression(0))
                val right = visitExpression(ctx.expression(1))
                val operator = OP_MAP[ctx.precedencePlusOperator().start.type]
                return BinaryOperatorField(ctx.getRawText()).feed(left, operator, right)
            }
            is PrecedenceConcatenateExpressionContext -> {
                val left = visitExpression(ctx.expression(0))
                val right = visitExpression(ctx.expression(1))
                val operator = OP_MAP[ctx.precedenceConcatenateOperator().start.type]
                return BinaryOperatorField(ctx.getRawText()).feed(left, operator, right)
            }
            is PrecedenceAmpersandExpressionContext -> {
                val left = visitExpression(ctx.expression(0))
                val right = visitExpression(ctx.expression(1))
                val operator = OP_MAP[ctx.precedenceAmpersandOperator().start.type]
                return BinaryOperatorField(ctx.getRawText()).feed(left, operator, right)
            }
            is PrecedenceBitwiseOrExpressionContext -> {
                val left = visitExpression(ctx.expression(0))
                val right = visitExpression(ctx.expression(1))
                val operator = OP_MAP[ctx.precedenceBitwiseOrOperator().start.type]
                return BinaryOperatorField(ctx.getRawText()).feed(left, operator, right)
            }
            is PrecedenceSimilarExpressionContext -> {
                val left = visitExpression(ctx.expression())
                var op: Token? = null
                var exp: ParserRuleContext? = null
                if (ctx.precedenceSimilarExpressionPart().precedenceSimilarOperator()?.also { op = it.start } != null) {
                    val right = visitExpression(ctx.precedenceSimilarExpressionPart().expression())
                    val operator = OP_MAP[op!!.type]
                    return BinaryOperatorField(ctx.getRawText()).feed(left, operator, right)
                } else if (ctx.precedenceSimilarExpressionPart().precedenceSimilarExpressionAtom()?.also { exp = it } != null) {
                    @Suppress("NAME_SHADOWING")
                    val exp = exp!! as PrecedenceSimilarExpressionAtomContext
                    if (exp.KW_IN() != null) {
                        val operator = PredicateOperator.IN
                        if (exp.precedenceSimilarExpressionIn().subQueryExpression() != null) {
                            val right = visitSelectStatement(exp.precedenceSimilarExpressionIn().subQueryExpression().selectStatement())
                            return MultiOperatorField(ctx.getRawText()).feed(left, operator, listOf(right))
                        } else if (exp.precedenceSimilarExpressionIn().expressionsInParenthesis() != null) {
                            val values = visitExpressionsInParenthesis(exp.precedenceSimilarExpressionIn().expressionsInParenthesis())
                            return MultiOperatorField(ctx.getRawText()).feed(left, operator, values)
                        } else {
                            throw WillNeverHappenException()
                        }
                    } else if (exp.KW_BETWEEN() != null) {
                        val operator = PredicateOperator.BETWEEN
                        val values = ArrayList<Expression>(2)
                        values.add(visitExpression(exp.expression(0)))
                        values.add(visitExpression(exp.expression(1)))
                        return MultiOperatorField(ctx.getRawText()).feed(left, operator, values)
                    } else if (exp.KW_LIKE() != null) {
                        val operator = if (exp.KW_ANY() != null) PredicateOperator.LIKE_ANY else PredicateOperator.LIKE_ALL
                        val values = visitExpressionsInParenthesis(exp.expressionsInParenthesis())
                        return MultiOperatorField(ctx.getRawText()).feed(left, operator, values)
                    } else {
                        throw WillNeverHappenException()
                    }
                } else if (ctx.precedenceSimilarExpressionPart().precedenceSimilarExpressionPartNot()?.also { exp = it } != null) {
                    @Suppress("NAME_SHADOWING")
                    val exp = exp!! as PrecedenceSimilarExpressionPartNotContext
                    if (exp.precedenceRegexpOperator() != null) {
                        val right = visitExpression(exp.expression())
                        val operator = if (exp.precedenceRegexpOperator().KW_LIKE() != null) PredicateOperator.NOT_LIKE
                        else if (exp.precedenceRegexpOperator().KW_RLIKE() != null) PredicateOperator.NOT_RLIKE
                        else if (exp.precedenceRegexpOperator().KW_REGEXP() != null) PredicateOperator.NOT_REGEXP
                        else throw WillNeverHappenException()
                        return BinaryOperatorField(ctx.getRawText()).feed(left, operator, right)
                    } else {
                        @Suppress("NAME_SHADOWING")
                        val exp = exp.precedenceSimilarExpressionAtom()
                        if (exp.KW_IN() != null) {
                            val operator = PredicateOperator.NOT_IN
                            if (exp.precedenceSimilarExpressionIn().subQueryExpression() != null) {
                                val right = visitSelectStatement(exp.precedenceSimilarExpressionIn().subQueryExpression().selectStatement())
                                return MultiOperatorField(ctx.getRawText()).feed(left, operator, listOf(right))
                            } else if (exp.precedenceSimilarExpressionIn().expressionsInParenthesis() != null) {
                                val values = visitExpressionsInParenthesis(exp.precedenceSimilarExpressionIn().expressionsInParenthesis())
                                return MultiOperatorField(ctx.getRawText()).feed(left, operator, values)
                            } else {
                                throw WillNeverHappenException()
                            }
                        } else if (exp.KW_BETWEEN() != null) {
                            val operator = PredicateOperator.NOT_BETWEEN
                            val values = ArrayList<Expression>(2)
                            values.add(visitExpression(exp.expression(0)))
                            values.add(visitExpression(exp.expression(1)))
                            return MultiOperatorField(ctx.getRawText()).feed(left, operator, values)
                        } else if (exp.KW_LIKE() != null) {
                            val operator = if (exp.KW_ANY() != null) PredicateOperator.NOT_LIKE_ANY else PredicateOperator.NOT_LIKE_ALL
                            val values = visitExpressionsInParenthesis(exp.expressionsInParenthesis())
                            return MultiOperatorField(ctx.getRawText()).feed(left, operator, values)
                        } else {
                            throw WillNeverHappenException()
                        }
                    }
                }
                throw WillNeverHappenException()
            }
            is PrecedenceExistsExpressionContext -> {
                val subQuery = visitSelectStatement(ctx.subQueryExpression().selectStatement())
                val operator = PredicateOperator.EXISTS
                return UnaryOperatorField(ctx.getRawText()).feed(subQuery, operator)
            }
            is PrecedenceEqualExpressionContext -> {
                val left = visitExpression(ctx.expression(0))
                val right = visitExpression(ctx.expression(1))
                if (ctx.precedenceEqualOperator() != null && ctx.precedenceEqualOperator().KW_IS() == null) {
                    val operator = OP_MAP[ctx.precedenceEqualOperator().start.type]
                    return BinaryOperatorField(ctx.getRawText()).feed(left, operator, right)
                } else if (ctx.precedenceEqualOperator()?.KW_IS() != null || ctx.precedenceDistinctOperator() != null){
                    val operator = if (ctx.precedenceDistinctOperator()!=null) PredicateOperator.IS_DISTINCT_FROM else PredicateOperator.NOT_DISTINCT_FROM
                    return BinaryOperatorField(ctx.getRawText()).feed(left, operator, right)
                }
                throw WillNeverHappenException()
            }
            is PrecedenceNotExpressionContext -> {
                val expr = visitExpression(ctx.expression())
                return UnaryOperatorField(ctx.getRawText()).feed(expr, LogicalOperator.NOT)
            }
            is PrecedenceAndExpressionContext -> {
                val left = visitExpression(ctx.expression(0))
                val right = visitExpression(ctx.expression(1))
                return BinaryOperatorField(ctx.getRawText()).feed(left, LogicalOperator.AND, right)
            }
            is PrecedenceOrExpressionContext -> {
                val left = visitExpression(ctx.expression(0))
                val right = visitExpression(ctx.expression(1))
                return BinaryOperatorField(ctx.getRawText()).feed(left, LogicalOperator.OR, right)
            }
            is PrecedenceParenExpressionContext -> {
                return visitExpression(ctx.expression())
            }
            else -> {
                throw WillNeverHappenException()
            }
        }
    }

    private fun visitAtomExpression(ctx: AtomExpressionContext): SelectExpr {
        with(ctx) {
            if (constant() != null) {
                return ConstantField(ctx.getRawText()).apply { setPosition(ctx) }
            } else if (tableOrColumn() != null) {
                val rawText = (ctx.parent as PrecedenceFieldExpressionContext).getRawText().replace("`", "")
                return AtomicField(rawText).apply { setPosition(ctx.parent as ParserRuleContext) }
            } else if (function() != null) {
                val funcName = ctx.function().functionName().getRawText()
                val params = mutableListOf<Expression>()
                function().STAR()?.also { params.add(AsteriskField("*")) }
                function().KW_DISTINCT()?.also { params.add(KeywordField(it.text)) }
                function().KW_ALL()?.also { params.add(KeywordField(it.text)) }
                for (sel in ctx.function().selectExpression()?: emptyList()) {
                    if (sel.expression()!=null) {
                        params.add(visitExpression(sel.expression()))
                    } else {
                        params.add(AsteriskField(sel.tableAllColumns().getRawText()))
                    }
                }
                val funcField = FunctionField(ctx.getRawText()).feed(funcName, params).apply { setPosition(ctx) }
                if (function().KW_OVER() != null) {
                    val windowField = visitWindowSpecification(function().window_specification())
                    return windowField.feed(funcField)
                }
                return funcField
            } else if (castExpression() != null) {
                val field = visitExpression(ctx.castExpression().expression())
                val dataType = DataType.of(ctx.castExpression().primitiveType().getRawText())
                return CastField(ctx.getRawText()).feed(field, dataType).apply { setPosition(ctx ) }
            } else if (extractExpression() != null) {
                val timeQualifier = TimeQualifier.of(extractExpression().timeQualifiers().getRawText())
                val timeField = visitExpression(extractExpression().expression())
                return ExtractField(ctx.getRawText()).feed(timeQualifier, timeField).apply { setPosition(ctx ) }
            } else if (floorExpression() != null) {
                val funcName = "FLOOR"
                val param = visitExpression(floorExpression().expression())
                return FunctionField(ctx.getRawText()).feed(funcName, listOf(param)).apply { setPosition(ctx ) }
            } else if (caseExpression() != null) {
                val expSize = caseExpression().expression().size
                val case = visitExpression(caseExpression().expression(0))
                val whenThenPair = mutableListOf<CaseField.WhenThen>()
                for ( i in 1 until expSize step 2) {
                    val whenExp = visitExpression(caseExpression().expression(i))
                    val thenExp = visitExpression(caseExpression().expression(i+1))
                    whenThenPair.add(CaseField.WhenThen(whenExp, thenExp))
                }
                val elseExp = if (expSize%2==0){ visitExpression(caseExpression().expression(expSize-1)) } else null
                return CaseField(ctx.getRawText()).feed(case, whenThenPair, elseExp).apply { setPosition(ctx ) }
            } else if (whenExpression() != null) {
                val expSize = whenExpression().expression().size
                val whenThenPair = mutableListOf<CaseField.WhenThen>()
                for ( i in 0 until expSize-1 step 2) {
                    val whenExp = visitExpression(whenExpression().expression(i))
                    val thenExp = visitExpression(whenExpression().expression(i+1))
                    whenThenPair.add(CaseField.WhenThen(whenExp, thenExp))
                }
                val elseExp = if (expSize%2==1) { visitExpression(whenExpression().expression(expSize-1)) } else null
                return CaseField(ctx.getRawText()).feed(null, whenThenPair, elseExp).apply { setPosition(ctx ) }
            } else if (subQueryExpression() != null) {
                return visitSelectStatement(ctx.subQueryExpression().selectStatement()).apply { setPosition(ctx ) }
            } else if (intervalExpression() != null) {
                with(intervalExpression()) {
                    val interval = intervalValue()?.let { ConstantField(it.getRawText()) }?:visitExpression(expression())
                    val qualifier = with(intervalQualifiers()) {
                        when {
                            KW_TO() != null -> if (KW_YEAR() != null) {
                                IntervalQualifier.YEAR_MONTH
                            } else {
                                IntervalQualifier.DAY_SECOND
                            }
                            else -> IntervalQualifier.valueOf(intervalQualifiers().text.toUpperCase())
                        }
                    }
                    return IntervalField(ctx.getRawText()).feed(interval, qualifier).apply { setPosition(ctx ) }
                }
            } else if (expressionsInParenthesis() != null) {
                val expressions = visitExpressionsInParenthesis(expressionsInParenthesis())
                if (expressions.size == 1 ){
                    return expressions[0].apply { setPosition(ctx.expressionsInParenthesis().expressionsNotInParenthesis().expression() ) }
                }
                throw IllegalSyntaxException("暂不支持这种语法：[" + expressionsInParenthesis().getRawText() + "]")
            } else {
                throw WillNeverHappenException()
            }
        }
    }

    private fun visitExpressionsInParenthesis(ctx: ExpressionsInParenthesisContext): List<SelectExpr> {
        val values = mutableListOf<SelectExpr>()
        values.add(visitExpression( ctx.expressionsNotInParenthesis().expression()))
        for (v in ctx.expressionsNotInParenthesis().expressionPart()?.expression()?: emptyList()) {
            values.add(visitExpression(v))
        }
        return values
    }

    private fun visitJoinSource(ctx: JoinSourceContext): FromItem {
        val fromTable = visitAtomJoinSource(ctx.atomjoinSource())
        if (ctx.joinPart()?.isNotEmpty() == true) {
            val joinParts = mutableListOf<JoinTable.JoinPart>()
            for (joinPartContext in ctx.joinPart()) {
                val joinType = joinPartContext.joinToken()
                val joinPart = joinPartContext.joinSourcePart()
                val joinTable = with(joinPart) {
                    when {
                        tableSource() != null -> visitTableSource(tableSource())
                        virtualTableSource() != null -> {
                            log.warn("VirtualTable Syntax Not Supported Yet")
                            EmptyTable(virtualTableSource().getRawText())
                        }
                        subQuerySource() != null ->
                            (visitQueryStatementExpression(subQuerySource().queryStatementExpression()) as SelectTable)
                                    .also { it.alias = subQuerySource().identifier()?.let { id-> getAlias(id, subQuerySource().KW_AS()!=null) } }
                        partitionedTableFunction() != null -> {
                            log.warn("Partitioned Table Function Syntax Not Supported Yet")
                            EmptyTable(partitionedTableFunction().getRawText())
                        }
                        else -> throw WillNeverHappenException()
                    }
                }
                val onExpr = joinPartContext.expression()?.let { visitExpression(it) }
                joinParts.add(JoinTable.JoinPart(JoinType.of(joinType.getRawText()), joinTable, onExpr))
            }
            return JoinTable(fromTable, joinParts)
        } else {
            return fromTable
        }
    }

    private fun visitAtomJoinSource(ctx: AtomjoinSourceContext): FromItem {
        for (lateralViewContext in ctx.lateralView()?: emptyList()) {
            visitLateralView(lateralViewContext)
        }
        with(ctx) {
            return when {
                tableSource() != null -> visitTableSource(tableSource())
                virtualTableSource() != null -> {
                    log.warn("VirtualTable Syntax Not Supported Yet")
                    EmptyTable(virtualTableSource().getRawText())
                }
                subQuerySource() != null -> {
                    val subQuery = visitQueryStatementExpression(subQuerySource().queryStatementExpression())
                    val alias = getAlias(subQuerySource().identifier(), subQuerySource().KW_AS() != null)
                    (subQuery as SelectTable).alias = alias
                    subQuery
                }
                partitionedTableFunction() != null -> {
                    log.warn("Partitioned Table Function Syntax Not Supported Yet")
                    EmptyTable(partitionedTableFunction().getRawText())
                }
                joinSource() != null -> visitJoinSource(joinSource())
                else -> throw WillNeverHappenException()
            }
        }
    }

    private fun visitTableSource(ctx: TableSourceContext): FromItem {
        val tableName = ctx.tableName().getRawText().trim('`')
        val tableAlias = ctx.identifier()?.let { getAlias(it, ctx.KW_AS()!=null) }
        if (this.withTables.containsKey(tableName)) {
            return this.withTables.getOrElse(tableName) { PhysicalTable(tableName).also { tableAlias?.apply { it.alias = this } } }
        }
        return PhysicalTable(tableName).also { tableAlias?.apply { it.alias = this } }
    }

    /**
     * 行转列方法
     */
    private fun visitLateralView(ctx: LateralViewContext) {
        //todo
    }

    private fun visitColType(ctx: ColTypeContext): DataType {
        val type = ctx.type_db_col()
        return when {
            type.primitiveType() != null -> DataType.of(type.primitiveType().getRawText())
            type.listType() != null -> DataType.ARRAY
            type.mapType() != null -> DataType.MAP
            else -> DataType.OTHER
        }
    }

    companion object {

        private val log = LoggerFactory.getLogger(HiveSqlParseListener::class.java)

        @JvmStatic
        val OP_MAP:Map<Int, Operator> = mapOf(
                STAR to CalculateOperator.MUL,
                DIV to CalculateOperator.DIV,
                MOD to CalculateOperator.MOD,
                DIVIDE to CalculateOperator.DIVIDE,
                PLUS to CalculateOperator.PLUS,
                MINUS to CalculateOperator.MINUS,

                EQUAL to PredicateOperator.EQUAL,
                EQUAL_NS to PredicateOperator.EQUAL_NS,
                NOTEQUAL to PredicateOperator.NOT_EQUAL,
                LESSTHANOREQUALTO to PredicateOperator.LESS_EQUAL,
                LESSTHAN to PredicateOperator.LESS,
                GREATERTHAN to PredicateOperator.GREATER,
                GREATERTHANOREQUALTO to PredicateOperator.GREATER_EQUAL,

                KW_LIKE to PredicateOperator.LIKE,
                KW_RLIKE to PredicateOperator.RLIKE,
                KW_REGEXP to PredicateOperator.REGEXP,

                KW_AND to LogicalOperator.AND,
                KW_OR to LogicalOperator.OR,
                KW_NOT to LogicalOperator.NOT,

                AMPERSAND to BitOperator.AND,
                TILDE to BitOperator.TILDE,
                BITWISEOR to BitOperator.OR,
                BITWISEXOR to BitOperator.XOR,

                CONCATENATE to CalculateOperator.CONCAT,

        )

    }
}