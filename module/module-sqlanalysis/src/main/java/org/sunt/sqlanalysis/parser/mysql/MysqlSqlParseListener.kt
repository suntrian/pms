package org.sunt.sqlanalysis.parser.mysql

import org.antlr.v4.runtime.TokenStream
import org.sunt.sqlanalysis.model.Table
import org.sunt.sqlanalysis.parser.SqlParseListener

/*
import java.util.*
import org.sunt.sqlanalysis.exception.SqlParseException
import org.sunt.sqlanalysis.exception.WillNeverHappenException
import org.sunt.sqlanalysis.model.*
import org.sunt.sqlanalysis.model.DeleteTable
import org.sunt.sqlanalysis.model.InsertTable
import org.sunt.sqlanalysis.model.SelectTable
import org.sunt.sqlanalysis.parser.mysql.grammar.MySqlParser.*
 */

import org.sunt.sqlanalysis.parser.mysql.grammar.MySqlParserBaseListener


internal class MysqlSqlParseListener(override val tokenStream: TokenStream): MySqlParserBaseListener(), SqlParseListener {

    override val tables: MutableList<Table> = mutableListOf()

/*
    private var withTables: Map<String, SelectTable> = emptyMap()

    private val variables: MutableMap<String, MutableList<Expression>> = TreeMap(String.CASE_INSENSITIVE_ORDER)

    private var currentBlock: Table? = null

    override fun exitDmlStatement(ctx: DmlStatementContext) {
        when {
            ctx.selectStatement() != null -> {
                this.tables.add(select(ctx.selectStatement()))
            }
            ctx.insertStatement() != null -> {
                val tableName = ctx.insertStatement().tableName().text
                val insertTable = InsertTable(tableName)
                this.tables.add(insertTable)
            }
            ctx.deleteStatement() != null -> {
                val deleteStatementContext = ctx.deleteStatement()
                when {
                    deleteStatementContext.singleDeleteStatement() != null -> {
                        val tableName = deleteStatementContext.singleDeleteStatement().tableName().text
                        val deleteTable = DeleteTable(tableName)
                        this.tables.add(deleteTable)
                    }
                    deleteStatementContext.multipleDeleteStatement() != null -> {
                        val tables = tableSources(deleteStatementContext.multipleDeleteStatement().tableSources())
                        for (table in tables) {
                            this.tables.add(DeleteTable(table.label))
                        }
                    }
                }
            }
            ctx.updateStatement() != null -> {
                val updateStatementContext = ctx.updateStatement()
                when {
                    updateStatementContext.singleUpdateStatement() != null -> {
                        val tableName = updateStatementContext.singleUpdateStatement().tableName().text
                        this.tables.add(UpdateTable(tableName).also { table -> updateStatementContext.singleUpdateStatement().uid()?.let { table.alias = it.text } })
                    }
                    updateStatementContext.multipleUpdateStatement() != null -> {
                        val tables = tableSources(updateStatementContext.multipleUpdateStatement().tableSources())
                        for (table in tables) {
                            this.tables.add(UpdateTable(table.label))
                        }
                    }
                }
            }
            ctx.replaceStatement() != null -> {
                val tableName = ctx.replaceStatement().tableName().text
                this.tables.add(UpdateTable(tableName))
            }
        }
    }

    override fun exitDdlStatement(ctx: DdlStatementContext) {
        when {
            ctx.createTable() != null -> this.tables.add(createTable(ctx.createTable()))
            ctx.createView() != null -> {
            }
            ctx.createProcedure() != null -> {
            }
            ctx.createFunction() != null -> {
            }
        }
    }


    override fun enterCreateProcedure(ctx: CreateProcedureContext) {
        ctx.fullId()?.text?.let { procedureName -> currentBlock = AnonymousTable(procedureName) }
    }

    override fun exitCreateProcedure(ctx: CreateProcedureContext) {
        this.currentBlock = null
    }

    override fun exitDeclareVariable(ctx: DeclareVariableContext?) {
        for (uidContext in ctx?.uidList()?.uid() ?: emptyList()) {
            val variable = uidContext.text
            val variableField = VariableField(variable, if (currentBlock != null && currentBlock is AnonymousTable) currentBlock as AnonymousTable else AnonymousTable(""))
            this.variables[variable] = mutableListOf(variableField)
        }
    }

    private fun createTable(ctx: CreateTableContext): CreateTable {
        return when (ctx) {
            is CopyCreateTableContext -> {
                // create table like
                val tableName = ctx.tableName(0).text
                val createTable = CreateTable(tableName).also {
                    it.isTemporary = (ctx.TEMPORARY() != null)
                }
                val likeTable = PhysicalTable(ctx.tableName(1).text)
                createTable.setSourceTable(likeTable)
                createTable
            }
            is QueryCreateTableContext -> {
                // create table as select
                val tableName = ctx.tableName().text
                val createTable = CreateTable(tableName).also {
                    it.isTemporary = ctx.TEMPORARY() != null
                    it.comment = ctx.tableOption().filterIsInstance(TableOptionCommentContext::class.java)
                        .map { i -> i.STRING_LITERAL()?.text }.firstOrNull()
                }
                val selectTable = select(ctx.selectStatement())
                if (ctx.createDefinitions()?.createDefinition()?.isNotEmpty() == true) {
                    val createFields = createDefinitions(ctx.createDefinitions(), createTable)
                    if (createFields.size != selectTable.fields.size) {
                        //todo check asterisk field
                    } else {
                        for (i in createFields.indices) {
                            createFields[i].getRelated().add(Relation(RelationType.DIRECTCOPY, selectTable.fields[i]))
                        }
                    }
                } else {
                    val createFields = AsteriskField(createTable)
                    createFields.getRelated().addAll(selectTable.fields.map { Relation(RelationType.DIRECTCOPY, it) })
                }
                this.tables.add(createTable)
                createTable
            }
            is ColumnCreateTableContext -> {
                val tableName = ctx.tableName().text
                val createTable = CreateTable(tableName).also { it ->
                    with(it) {
                        isColumnCreateTable = true
                        temporary = ctx.TEMPORARY() != null
                        comment = ctx.tableOption().filterIsInstance(TableOptionCommentContext::class.java)
                            .map { i -> i.STRING_LITERAL()?.text }
                            .firstOrNull()
                        createDefinitions(ctx.createDefinitions(), it)
                    }
                }
                createTable
            }
            else -> {
                throw WillNeverHappenException("")
            }
        }
    }

    private fun createDefinitions(ctx: CreateDefinitionsContext, createTable: CreateTable): List<CreateField> {
        val createFields = mutableListOf<CreateField>()
        val primaryKeys = mutableListOf<String>()
        val uniqueKeys = mutableListOf<String>()
        val foreignKeys = mutableListOf<String>()
        val indexKeys = mutableListOf<String>()
        for (createDefinition in ctx.createDefinition()) {
            when (createDefinition) {
                is ColumnDeclarationContext -> {
                    val fieldName = createDefinition.uid().text
                    val createField = CreateField(fieldName, createTable)
                    with(createField) {
                        val dataTypeLengthPrecision = dataType(createDefinition.columnDefinition().dataType())
                        dataType = DataType.of(dataTypeLengthPrecision.first)
                        dataLength = dataTypeLengthPrecision.second
                        dataPrecision = dataTypeLengthPrecision.third
                        for (columnConstraintContext in createDefinition.columnDefinition().columnConstraint()) {
                            when (columnConstraintContext) {
                                is NullColumnConstraintContext -> isNullable = columnConstraintContext.nullNotnull().NOT() == null
                                is DefaultColumnConstraintContext -> defaultValue = columnConstraintContext.defaultValue().text
                                is AutoIncrementColumnConstraintContext -> isAutoIncrement = columnConstraintContext.AUTO_INCREMENT() != null
                                is PrimaryKeyColumnConstraintContext -> isPrimaryKey = true
                                is UniqueKeyColumnConstraintContext -> isUnique = true
                                is CommentColumnConstraintContext -> comment = columnConstraintContext.STRING_LITERAL().text
                                is ReferenceDefinitionContext -> {/* todo */
                                }
                            }
                        }

                    }
                    createFields.add(createField)
                }
                is ConstraintDeclarationContext -> {
                    when (val tableConstraint = createDefinition.tableConstraint()) {
                        is PrimaryKeyTableConstraintContext -> {
                            for (indexColumnNameContext in tableConstraint.indexColumnNames().indexColumnName()) {
                                primaryKeys.add(indexColumnNameContext.text)
                            }
                        }
                        is UniqueKeyTableConstraintContext -> {
                            for (indexColumnNames in tableConstraint.indexColumnNames().indexColumnName()) {
                                primaryKeys.add(indexColumnNames.text)
                            }
                        }
                        is ForeignKeyTableConstraintContext -> {
                            for (indexColumnNameContext in tableConstraint.indexColumnNames().indexColumnName()) {
                                foreignKeys.add(indexColumnNameContext.text)
                            }
                        }
                    }
                }
                is IndexDeclarationContext -> {
                    when (val indexColumnDefinition = createDefinition.indexColumnDefinition()) {
                        is SimpleIndexDeclarationContext -> {
                            for (indexColumnNameContext in indexColumnDefinition.indexColumnNames().indexColumnName()) {
                                indexKeys.add(indexColumnNameContext.text)
                            }
                        }
                        is SpecialIndexDeclarationContext -> {
                            for (indexColumnNameContext in indexColumnDefinition.indexColumnNames().indexColumnName()) {
                                indexKeys.add(indexColumnNameContext.text)
                            }
                        }
                    }
                }
            }
        }
        for (field in createFields) {
            if (primaryKeys.contains(field.columnName)) {
                field.isPrimaryKey = true
            }
            if (uniqueKeys.contains(field.columnName)) {
                field.isUnique = true
            }
            if (foreignKeys.contains(field.columnName)) {
                field.isForeignKey = true
            }
            if (indexKeys.contains(field.columnName)) {
                field.isIndexKey = true
            }
        }
        return createFields
    }

    private fun dataType(ctx: DataTypeContext): Triple<String, Int?, Int?> {
        return when (ctx) {
            is StringDataTypeContext -> Triple(ctx.typeName.text, ctx.lengthOneDimension()?.decimalLiteral()?.text?.toInt(), null)
            is NationalStringDataTypeContext -> Triple(ctx.typeName.text, ctx.lengthOneDimension()?.decimalLiteral()?.text?.toInt(), null)
            is NationalVaryingStringDataTypeContext -> Triple(ctx.typeName.text, ctx.lengthOneDimension()?.decimalLiteral()?.text?.toInt(), null)
            is DimensionDataTypeContext -> {
                val length = ctx.lengthOneDimension()?.decimalLiteral()?.text?.toInt() ?: ctx.lengthTwoDimension()?.decimalLiteral(0)?.text?.toInt()
                ?: ctx.lengthTwoOptionalDimension()?.decimalLiteral(0)?.text?.toInt()
                val precision = ctx.lengthTwoDimension()?.decimalLiteral(1)?.text?.toInt() ?: ctx.lengthTwoOptionalDimension()?.decimalLiteral()?.getOrNull(1)?.text?.toInt()
                Triple(ctx.typeName.text, length, precision)
            }
            is SimpleDataTypeContext -> Triple(ctx.typeName.text, null, null)
            is CollectionDataTypeContext -> Triple(ctx.typeName.text, null, null)
            is SpatialDataTypeContext -> Triple(ctx.typeName.text, null, null)
            is LongVarcharDataTypeContext -> Triple(ctx.typeName.text, null, null)
            is LongVarbinaryDataTypeContext -> Triple(ctx.text, null, null)
            else -> throw WillNeverHappenException("")
        }
    }

    private fun select(ctx: SelectStatementContext): SelectTable {
        var selectTableList: MutableList<SelectTable>?
        when (ctx) {
            is SimpleSelectContext -> {
                return querySpecification(ctx.querySpecification())
            }
            is ParenthesisSelectContext -> {
                return queryExpression(ctx.queryExpression())
            }
            is UnionSelectContext -> {
                selectTableList = mutableListOf()
                selectTableList.add(querySpecificationNointo(ctx.querySpecificationNointo()))
                for (unionStatementContext in ctx.unionStatement()) {
                    if (unionStatementContext.querySpecificationNointo() != null) {
                        selectTableList.add(querySpecificationNointo(unionStatementContext.querySpecificationNointo()))
                    } else if (unionStatementContext.queryExpressionNointo() != null) {
                        selectTableList.add(queryExpressionNointo(unionStatementContext.queryExpressionNointo()))
                    }
                }
                if (ctx.querySpecification() != null) {
                    selectTableList.add(querySpecification(ctx.querySpecification()))
                } else if (ctx.queryExpression() != null) {
                    selectTableList.add(queryExpression(ctx.queryExpression()))
                }
            }
            is UnionParenthesisSelectContext -> {
                selectTableList = mutableListOf()
                selectTableList.add(queryExpressionNointo(ctx.queryExpressionNointo()))
                for (unionParenthesis in ctx.unionParenthesis()) {
                    if (unionParenthesis.queryExpressionNointo() != null) {
                        selectTableList.add(queryExpressionNointo(unionParenthesis.queryExpressionNointo()))
                    }
                }
                if (ctx.queryExpression() != null) {
                    selectTableList.add(queryExpression(ctx.queryExpression()))
                }
            }
            else -> {
                throw WillNeverHappenException("not supposed to here")
            }
        }
        val unionTable = SetOperatorTable().(selectTableList)
        val fieldSize = selectTableList.map { it.fields }.filter { f -> f.none { x -> x is AsteriskField } }.map { it.size }.toSet()
        if (fieldSize.size > 1) {
            throw SqlParseException("union查询的字段个数不一致")
        } else if (fieldSize.isEmpty()) {
            unionTable.allAsteriskField = true
            val maxFieldSize = selectTableList.map { it.fields.size }.maxOf { x -> x }
            if (maxFieldSize > 1) {
                throw IllegalStateException("暂不处理查询多个*字段的情况")
            }
            val unionField = UnionField("*", unionTable)
            return unionTable
        }

        for (i in 0 until fieldSize.iterator().next()) {
            val tempFields = LinkedList<SelectItem>()
            for (selectTable in selectTableList) {
                val field = selectTable.fields[i]
                if (field is AsteriskField) {
                    tempFields.add(field)
                } else {
                    tempFields.add(field)
                }
            }
            unionTable.fields.toMutableList().add(UnionField("", unionTable))
        }
        return unionTable
    }

    private fun querySpecification(ctx: QuerySpecificationContext): SelectTable {
        return generateSelectTable(ctx.fromClause(), ctx.selectElements())
    }

    private fun queryExpression(ctx: QueryExpressionContext): SelectTable {
        var sub = ctx
        while (sub.querySpecification() == null) {
            sub = sub.queryExpression()
        }
        return querySpecification(sub.querySpecification())
    }

    private fun querySpecificationNointo(ctx: QuerySpecificationNointoContext): SelectTable {
        return generateSelectTable(ctx.fromClause(), ctx.selectElements())
    }

    private fun queryExpressionNointo(ctx: QueryExpressionNointoContext): SelectTable {
        var sub = ctx
        while (sub.querySpecificationNointo() == null) {
            sub = sub.queryExpressionNointo()
        }
        return querySpecificationNointo(ctx.querySpecificationNointo())
    }

    private fun generateSelectTable(from: FromClauseContext, selects: SelectElementsContext): SelectTable {
        val innerTables = from(from)
        val selectTable = SelectTable(innerTables)
        val selectFields = selectElements(selects, selectTable)
        val where = where(from)
        val groupBy = groupBy(from)
        val having = having(from)
        for (selectField in selectFields) {

        }

        return selectTable
    }

    private fun from(from: FromClauseContext): List<FromItem> {
        return tableSources(from.tableSources())
    }

    private fun where(from: FromClauseContext): SelectExpr? {
        if (from.WHERE() != null) {
            return expression(from.whereExpr).map { Pair(RelationType.CONDITION, it.second) }
        }
        return null
    }

    private fun groupBy(from: FromClauseContext): List<Pair<RelationType, FullFieldName>> {
        if (from.groupByItem() != null) {
            val fields = mutableListOf<Pair<RelationType, FullFieldName>>()
            for (groupByItemContext in from.groupByItem()) {
                fields.addAll(expression(groupByItemContext.expression()))
            }
            return fields
        }
        return emptyList()
    }

    private fun having(from: FromClauseContext): List<Pair<RelationType, FullFieldName>> {
        if (from.havingExpr != null) {
            return expression(from.havingExpr).map { Pair(RelationType.CONDITION, it.second) }
        }
        return emptyList()
    }

    private fun tableSources(tableSourcesContext: TableSourcesContext): List<FromItem> {
        val tableList = mutableListOf<FromItem>()
        for (tableSourceContext in tableSourcesContext.tableSource()) {
            var joinPart: List<JoinPartContext> = emptyList()
            when (tableSourceContext) {
                is TableSourceBaseContext -> {
                    tableList.addAll(tableSourceItem(tableSourceContext.tableSourceItem()))
                    joinPart = tableSourceContext.joinPart()
                }
                is TableSourceNestedContext -> {
                    tableList.addAll(tableSourceItem(tableSourceContext.tableSourceItem()))
                    joinPart = tableSourceContext.joinPart()
                }
            }
            for (joinPartContext in joinPart) {
                when (joinPartContext) {
                    is InnerJoinContext -> {
                        tableList.addAll(tableSourceItem(joinPartContext.tableSourceItem()))
                    }
                    is StraightJoinContext -> {
                        tableList.addAll(tableSourceItem(joinPartContext.tableSourceItem()))
                    }
                    is OuterJoinContext -> {
                        tableList.addAll(tableSourceItem(joinPartContext.tableSourceItem()))
                    }
                    is NaturalJoinContext -> {
                        tableList.addAll(tableSourceItem(joinPartContext.tableSourceItem()))
                    }
                }

            }
        }
        return tableList
    }

    private fun tableSourceItem(tableSourceItem: TableSourceItemContext): List<FromItem> {
        when (tableSourceItem) {
            is AtomTableItemContext -> {
                val tableFullName = tableSourceItem.tableName().text
                val table = PhysicalTable(parseTableName(tableFullName))
                tableSourceItem.alias?.text?.let { table.alias = Alias(it) }

                return Collections.singletonList(table)
            }
            is SubqueryTableItemContext -> {
                val table = select(tableSourceItem.selectStatement())
                tableSourceItem.alias?.text?.let { table.alias = Alias(it) }
                return Collections.singletonList(table)
            }
            is TableSourcesItemContext -> {
                return tableSources(tableSourceItem.tableSources())
            }
            else -> {
                throw WillNeverHappenException("will not come here")
            }
        }
    }

    private fun selectElements(selects: SelectElementsContext, selectTable: SelectTable): List<LogicalField> {
        val fields = mutableListOf<LogicalField>()
        if (selects.star != null) {
            fields.add(AsteriskField(selectTable))
        }
        for (selectElementContext in selects.selectElement()) {
            when (selectElementContext) {
                is SelectStarElementContext -> {
                    //todo
                    fields.add(AsteriskField(selectTable))
                }
                is SelectColumnElementContext -> {
                    val columnFullName = parseFieldName(selectElementContext.fullColumnName().text)
                    val selectField = SelectField(columnFullName.field, selectTable)
                    selectElementContext.uid()?.text?.let { selectField.alias = it }
                    fields.add(selectField)
                }
                is SelectFunctionElementContext -> {
                    val expression = selectElementContext.functionCall().text
                    val selectField = SelectField(expression, selectTable)
                    selectElementContext.uid()?.text?.let { selectField.alias = it }

                    fields.add(selectField)
                    val innerFields = functionCall(selectElementContext.functionCall())
                    //todo
                }
                is SelectExpressionElementContext -> {
                    val expression = selectElementContext.expression().text
                    val selectField = SelectField(expression, selectTable)
                    selectElementContext.uid()?.text?.let { selectField.alias = it }

                    fields.add(selectField)
                    val innerfields = expression(selectElementContext.expression())
                    //todo
                }
                else -> {
                    throw WillNeverHappenException("will not come here")
                }
            }
        }
        return fields
    }

    private fun functionCall(functionCall: FunctionCallContext): SelectExpr {
        return when (functionCall) {
            is SpecificFunctionCallContext -> {
                when (val specificFunction = functionCall.specificFunction()) {
                    is DataTypeFunctionCallContext -> expression(specificFunction.expression())
                    is ValuesFunctionCallContext -> listOf(Pair(RelationType.DIRECTCOPY, parseFieldName(specificFunction.fullColumnName().text)))
                    is CaseFunctionCallContext -> {
                        val result = mutableListOf<Pair<RelationType, FullFieldName>>()
                        specificFunction.expression()?.let { result.addAll(expression(it)) }
                        for (caseFuncAlternativeContext in specificFunction.caseFuncAlternative()) {
                            result.addAll(functionArg(caseFuncAlternativeContext.condition))
                            result.addAll(functionArg(caseFuncAlternativeContext.consequent))
                        }
                        specificFunction.functionArg()?.let { result.addAll(functionArg(it)) }
                        return result
                    }
                    is CharFunctionCallContext -> functionArgs(specificFunction.functionArgs())
                    is PositionFunctionCallContext -> {
                        specificFunction.expression()?.flatMap { expression(it).asIterable() }?.toList().orEmpty()
                    }
                    is SubstrFunctionCallContext -> {
                        specificFunction.expression()?.flatMap { expression(it).asIterable() }?.toList().orEmpty()
                    }
                    is TrimFunctionCallContext -> {
                        specificFunction.expression()?.flatMap { expression(it).asIterable() }?.toList().orEmpty()
                    }
                    is WeightFunctionCallContext -> specificFunction.expression()?.let { expression(it) }.orEmpty()
                    is ExtractFunctionCallContext -> specificFunction.expression()?.let { expression(it) }.orEmpty()
                    is GetFormatFunctionCallContext -> emptyList()
                    is SimpleFunctionCallContext -> emptyList()
                    else -> emptyList()
                }
            }
            is AggregateFunctionCallContext -> {
                when {
                    functionCall.aggregateWindowedFunction().functionArg() != null -> {
                        functionArg(functionCall.aggregateWindowedFunction().functionArg())
                    }
                    functionCall.aggregateWindowedFunction().functionArgs() != null -> {
                        functionArgs(functionCall.aggregateWindowedFunction().functionArgs())
                    }
                    else -> emptyList()
                }
            }
            is ScalarFunctionCallContext -> functionCall.functionArgs()?.let { functionArgs(it) }.orEmpty()
            is UdfFunctionCallContext -> functionCall.functionArgs()?.let { functionArgs(it) }.orEmpty()
            else -> emptyList()
        }
    }

    private fun expression(expression: ExpressionContext): SelectExpr {
        return when (expression) {
            is NotExpressionContext -> expression(expression.expression())
            is LogicalExpressionContext ->
                listOf(expression(expression.expression(0)), expression(expression.expression(1)))
                    .flatMap { it.asIterable() }
                    .toList()
            is IsExpressionContext -> predicate(expression.predicate())
            is PredicateExpressionContext -> predicate(expression.predicate())
            else -> throw WillNeverHappenException("will not come here")

        }
    }

    private fun expressionAtom(expression: ExpressionAtomContext): SelectExpr {
        return when (expression) {
            is FullColumnNameExpressionAtomContext -> listOf(Pair(RelationType.DIRECTCOPY, parseFieldName(expression.fullColumnName().text)))
            is FunctionCallExpressionAtomContext -> functionCall(expression.functionCall())
            is CollateExpressionAtomContext -> expressionAtom(expression.expressionAtom())
            is UnaryExpressionAtomContext -> expressionAtom(expression.expressionAtom())
            is BinaryExpressionAtomContext -> expressionAtom(expression.expressionAtom())
            is NestedExpressionAtomContext -> expression.expression().flatMap { expression(it).asIterable() }.toList()
            is NestedRowExpressionAtomContext -> expression.expression().flatMap { expression(it).asIterable() }.toList()
            is SubqueryExpessionAtomContext -> emptyList()
            is IntervalExpressionAtomContext -> expression(expression.expression())
            is BitExpressionAtomContext -> listOf(expression.left, expression.right).flatMap { expressionAtom(it).asIterable() }.toList()
            is MathExpressionAtomContext -> listOf(expression.left, expression.right).flatMap { expressionAtom(it).asIterable() }.toList()
            is JsonExpressionAtomContext -> listOf(expression.left, expression.right).flatMap { expressionAtom(it).asIterable() }.toList()
            else -> emptyList()
        }
    }

    private fun predicate(predicate: PredicateContext): SelectExpr {
        return when (predicate) {
            is InPredicateContext -> emptyList()
            is IsNullPredicateContext -> emptyList()
            is BinaryComparasionPredicateContext -> emptyList()
            is SubqueryComparasionPredicateContext -> emptyList()
            is BetweenPredicateContext -> emptyList()
            is SoundsLikePredicateContext -> emptyList()
            is LikePredicateContext -> emptyList()
            is RegexpPredicateContext -> emptyList()
            is ExpressionAtomPredicateContext -> expressionAtom(predicate.expressionAtom())
            is JsonMemberOfPredicateContext -> emptyList()
            else -> emptyList()
        }
    }

    private fun functionArgs(args: FunctionArgsContext): List<SelectExpr> {
        val result = LinkedList<SelectExpr>()
        if (args.fullColumnName() != null) {
            for (fullColumnNameContext in args.fullColumnName()) {
                result.add(AtomicField(fullColumnNameContext.text))
            }
        }
        if (args.functionCall() != null) {
            for (functionCallContext in args.functionCall()) {
                result.addAll(functionCall(functionCallContext))
            }
        }
        if (args.expression() != null) {
            for (expressionContext in args.expression()) {
                result.addAll(expression(expressionContext))
            }
        }
        return result
    }

    private fun functionArg(arg: FunctionArgContext): SelectExpr {
        return when {
            arg.fullColumnName() != null -> AtomicField(arg.text)
            arg.expression() != null -> expression(arg.expression())
            arg.functionCall() != null -> functionCall(arg.functionCall())
            else -> throw WillNeverHappenException()
        }
    }

    companion object {

        fun unwrapId(id: String): String {
            if (id.startsWith('`') && id.endsWith('`')) {
                return id.substring(1, id.length - 1)
            }
            return id
        }

        fun parseFieldName(fullId: String): ColumnName {
            val ids = fullId.split('.')
            return when (ids.size) {
                1 -> ColumnName(null, null, null, unwrapId(ids[0]))
                2 -> ColumnName(null, null, unwrapId(ids[0]), unwrapId(ids[1]))
                3 -> ColumnName(null, unwrapId(ids[0]), unwrapId(ids[1]), unwrapId(ids[2]))
                4 -> ColumnName(unwrapId(ids[0]), unwrapId(ids[1]), unwrapId(ids[2]), unwrapId(ids[3]))
                else -> throw WillNeverHappenException("")
            }
        }

        fun parseTableName(fullId: String): TableName {
            val ids = fullId.split('.')
            return when (ids.size) {
                1 -> TableName(null, null, unwrapId(ids[0]))
                2 -> TableName(null, unwrapId(ids[0]), unwrapId(ids[1]))
                3 -> TableName(unwrapId(ids[0]), unwrapId(ids[1]), unwrapId(ids[2]))
                else -> throw WillNeverHappenException("")
            }
        }

        fun qualifyTable(fullFieldName: ColumnName, tables: Collection<FromItem>): FromItem {
            if (fullFieldName.table == null) {
                if (tables.size > 1) {
                    return AmbiguousTable(tables)
                } else if (tables.size == 1) {
                    return tables.iterator().next()
                } else {
                    //todo 可能是变量
                    throw SqlParseException("字段${fullFieldName}未找到对应的表")
                }
            }
            for (table in tables) {
                if (fullFieldName.table == table.alias) {
                    return table
                }
            }
            throw SqlParseException("字段${fullFieldName}未找到对应的表")
        }

    }

*/
}
