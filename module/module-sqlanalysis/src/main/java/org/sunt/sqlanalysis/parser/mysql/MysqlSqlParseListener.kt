package org.sunt.sqlanalysis.parser.mysql

import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.TokenStream
import org.antlr.v4.runtime.tree.TerminalNode
import org.sunt.sqlanalysis.model.Table
import org.sunt.sqlanalysis.parser.SqlParseListener

import java.util.*
import org.sunt.sqlanalysis.exception.WillNeverHappenException
import org.sunt.sqlanalysis.model.*
import org.sunt.sqlanalysis.model.DeleteTable
import org.sunt.sqlanalysis.model.InsertTable
import org.sunt.sqlanalysis.model.SelectTable
import org.sunt.sqlanalysis.parser.mysql.grammar.MySqlParser.*

import org.sunt.sqlanalysis.parser.mysql.grammar.MySqlParserBaseListener


internal class MysqlSqlParseListener(override val tokenStream: TokenStream) : MySqlParserBaseListener(),
    SqlParseListener {

    override val tables: MutableList<Table> = mutableListOf()

    //private var withTables: Map<String, SelectTable> = emptyMap()

    private val variables: MutableMap<String, MutableList<Expression>> = TreeMap(String.CASE_INSENSITIVE_ORDER)

    private var currentBlock: Table? = null

    override fun exitInsertStatement(ctx: InsertStatementContext) {
        val tableName = ctx.tableName().getRawText()
        val insertTable = InsertTable(tableName).apply {
            if (ctx.columns != null) {
                addFields(ctx.columns.uid().map { AtomicField(it.text) })
                this.isRealFields = true
            }
            if (ctx.insertStatementValue().selectStatement() != null) {
                val selectTable = select(ctx.insertStatementValue().selectStatement())
                setSourceTable(selectTable)
            }
        }
        this.tables.add(insertTable)
    }

    override fun exitDeleteStatement(ctx: DeleteStatementContext) {
        if (ctx.singleDeleteStatement() != null) {
            val tableName = ctx.singleDeleteStatement().tableName().getRawText()
            val deleteTable = DeleteTable(tableName).apply {
                where = ctx.singleDeleteStatement().expression()?.let { expression(it) }
            }
            this.tables.add(deleteTable)
        } else if (ctx.multipleDeleteStatement() != null) {
            val sourceTable = tableSources(ctx.multipleDeleteStatement().tableSources())
            for (tableNameContext in ctx.multipleDeleteStatement().tableName()) {
                val deleteTable = DeleteTable(tableNameContext.getRawText(),
                    ctx.multipleDeleteStatement().expression()?.let { expression(it) }).apply {
                    this.setSourceTable(sourceTable)
                }
                this.tables.add(deleteTable)
            }
        }
    }

    override fun exitUpdateStatement(ctx: UpdateStatementContext) {
        if (ctx.singleUpdateStatement() != null) {
            val tableName = ctx.singleUpdateStatement().tableName().getRawText()
            this.tables.add(UpdateTable(tableName).also { table ->
                ctx.singleUpdateStatement().uid()?.let { table.alias = Alias(it.text) }
                table.where = ctx.singleUpdateStatement().expression()?.let { expression(it) }
            })
        } else if (ctx.multipleUpdateStatement() != null) {
            val sourceTable = tableSources(ctx.multipleUpdateStatement().tableSources())
            this.tables.add(UpdateTable("").apply {
                setSourceTable(sourceTable)
                this.where = ctx.multipleUpdateStatement().expression()?.let { expression(it) }
            })
        }
    }

    override fun exitReplaceStatement(ctx: ReplaceStatementContext) {
        val tableName = ctx.tableName().getRawText()
        val updateTable = UpdateTable(tableName).apply {
            if (ctx.insertStatementValue().selectStatement() != null) {
                setSourceTable(select(ctx.insertStatementValue().selectStatement()))
            }
        }
        this.tables.add(updateTable)
    }

    override fun exitSimpleSelect(ctx: SimpleSelectContext) {
        if (ctx.parent is DmlStatementContext) {
            this.tables.add(select(ctx))
        }
    }

    override fun exitParenthesisSelect(ctx: ParenthesisSelectContext) {
        if (ctx.parent is DmlStatementContext) {
            this.tables.add(select(ctx))
        }
    }

    override fun exitUnionSelect(ctx: UnionSelectContext) {
        if (ctx.parent is DmlStatementContext) {
            this.tables.add(select(ctx))
        }
    }

    override fun exitUnionParenthesisSelect(ctx: UnionParenthesisSelectContext) {
        if (ctx.parent is DmlStatementContext) {
            this.tables.add(select(ctx))
        }
    }

    override fun enterCreateProcedure(ctx: CreateProcedureContext) {
        ctx.fullId()?.text?.let { procedureName -> currentBlock = AnonymousTable(procedureName) }
    }

    override fun exitCreateProcedure(ctx: CreateProcedureContext) {
        this.currentBlock = null
    }

    override fun exitDeclareVariable(ctx: DeclareVariableContext) {
        for (uidContext in ctx.uidList()?.uid() ?: emptyList()) {
            val variable = uidContext.text
            val variableField = VariableField(variable).apply {
                val dataTypeLengthPrecision = dataType(ctx.dataType())
                dataType = DataType.of(dataTypeLengthPrecision.first)
            }
            if (currentBlock != null && currentBlock is AnonymousTable) {
                (currentBlock as AnonymousTable).addField(variableField)
            }
            this.variables[variable] = mutableListOf(variableField)
        }
    }

    override fun exitCopyCreateTable(ctx: CopyCreateTableContext) {
        // create table like
        val tableName = ctx.tableName(0).text
        val createTable = CreateTable(tableName).also {
            it.isTemporary = (ctx.TEMPORARY() != null)
            val likeTable = PhysicalTable(ctx.tableName(1).text)
                .addField(AsteriskField("*").setPhysical(true))
            it.setSourceTable(likeTable)
            it.addField(AsteriskField("*"))
        }
        this.tables.add(createTable)
    }

    override fun exitQueryCreateTable(ctx: QueryCreateTableContext) {
        // create table as select
        val tableName = ctx.tableName().text
        val createTable = CreateTable(tableName).also {
            it.isTemporary = ctx.TEMPORARY() != null
            it.comment = ctx.tableOption().filterIsInstance(TableOptionCommentContext::class.java)
                .map { i -> i.STRING_LITERAL()?.text }.firstOrNull()
            it.setSourceTable(select(ctx.selectStatement()))
        }
        if (ctx.createDefinitions()?.createDefinition()?.isNotEmpty() == true) {
            val createFields = createDefinitions(ctx.createDefinitions())
            createTable.addField(createFields)
        } else {
            val createField = AsteriskField("*")
            createTable.addField(createField)
        }
        this.tables.add(createTable)
    }

    override fun exitColumnCreateTable(ctx: ColumnCreateTableContext) {
        val tableName = ctx.tableName().text
        val createTable = CreateTable(tableName).also {
            it.isTemporary = ctx.TEMPORARY() != null
            it.comment = ctx.tableOption().filterIsInstance(TableOptionCommentContext::class.java)
                .map { i -> i.STRING_LITERAL()?.text }
                .firstOrNull()
            it.addField(createDefinitions(ctx.createDefinitions()))
        }
        this.tables.add(createTable)
    }

    private fun createDefinitions(ctx: CreateDefinitionsContext): List<CreateField> {
        val createFields = mutableListOf<CreateField>()
        val primaryKeys = mutableListOf<String>()
        val uniqueKeys = mutableListOf<String>()
        val foreignKeys = mutableListOf<String>()
        val indexKeys = mutableListOf<String>()
        for (createDefinition in ctx.createDefinition()) {
            when (createDefinition) {
                is ColumnDeclarationContext -> {
                    val fieldName = createDefinition.uid().text
                    val createField = CreateField(fieldName).apply {
                        val dataTypeLengthPrecision = dataType(createDefinition.columnDefinition().dataType())
                        dataType = DataType.of(dataTypeLengthPrecision.first)
                        dataLength = dataTypeLengthPrecision.second
                        dataPrecision = dataTypeLengthPrecision.third
                        for (columnConstraintContext in createDefinition.columnDefinition().columnConstraint()) {
                            when (columnConstraintContext) {
                                is NullColumnConstraintContext -> isNullable =
                                    columnConstraintContext.nullNotnull().NOT() == null
                                is DefaultColumnConstraintContext -> defaultValue =
                                    columnConstraintContext.defaultValue().text
                                is AutoIncrementColumnConstraintContext -> isAutoIncrement =
                                    columnConstraintContext.AUTO_INCREMENT() != null
                                is PrimaryKeyColumnConstraintContext -> isPrimaryKey = true
                                is UniqueKeyColumnConstraintContext -> isUnique = true
                                is CommentColumnConstraintContext -> comment =
                                    columnConstraintContext.STRING_LITERAL().text
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
            is StringDataTypeContext -> Triple(
                ctx.typeName.text,
                ctx.lengthOneDimension()?.decimalLiteral()?.text?.toInt(),
                null
            )
            is NationalStringDataTypeContext -> Triple(
                ctx.typeName.text,
                ctx.lengthOneDimension()?.decimalLiteral()?.text?.toInt(),
                null
            )
            is NationalVaryingStringDataTypeContext -> Triple(
                ctx.typeName.text,
                ctx.lengthOneDimension()?.decimalLiteral()?.text?.toInt(),
                null
            )
            is DimensionDataTypeContext -> {
                val length = ctx.lengthOneDimension()?.decimalLiteral()?.text?.toInt() ?: ctx.lengthTwoDimension()
                    ?.decimalLiteral(0)?.text?.toInt()
                ?: ctx.lengthTwoOptionalDimension()?.decimalLiteral(0)?.text?.toInt()
                val precision =
                    ctx.lengthTwoDimension()?.decimalLiteral(1)?.text?.toInt() ?: ctx.lengthTwoOptionalDimension()
                        ?.decimalLiteral()?.getOrNull(1)?.text?.toInt()
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
        when (ctx) {
            is SimpleSelectContext -> return querySpecification(ctx.querySpecification())
            is ParenthesisSelectContext -> return queryExpression(ctx.queryExpression())
            is UnionSelectContext -> {
                return SetOperatorTable(ctx.getRawText()).apply {
                    firstTable = querySpecificationNointo(ctx.querySpecificationNointo())
                    for (stmt in ctx.unionStatement()) {
                        if (stmt.querySpecificationNointo() != null) {
                            val setTable = SetOperatorTable.SetTable(
                                stmt.ALL()?.let { SetOperator.UNION_ALL } ?: SetOperator.UNION_DISTINCT,
                                querySpecificationNointo(stmt.querySpecificationNointo())
                            )
                            this.addSetTable(setTable)
                        } else if (stmt.queryExpressionNointo() != null) {
                            val setTable = SetOperatorTable.SetTable(
                                stmt.ALL()?.let { SetOperator.UNION_ALL } ?: SetOperator.UNION_DISTINCT,
                                queryExpressionNointo(stmt.queryExpressionNointo())
                            )
                            this.addSetTable(setTable)
                        }
                    }
                    if (ctx.UNION() != null) {
                        if (ctx.querySpecification() != null) {
                            val setTable = SetOperatorTable.SetTable(
                                ctx.ALL()?.let { SetOperator.UNION_ALL } ?: SetOperator.UNION_DISTINCT,
                                querySpecification(ctx.querySpecification())
                            )
                            this.addSetTable(setTable)
                        } else {
                            val setTable = SetOperatorTable.SetTable(
                                ctx.ALL()?.let { SetOperator.UNION_ALL } ?: SetOperator.UNION_DISTINCT,
                                queryExpression(ctx.queryExpression())
                            )
                            this.addSetTable(setTable)
                        }
                    }
                    if (ctx.orderByClause() != null) {
                        this.orderBys = orderBys(ctx.orderByClause())
                    }
                }
            }
            is UnionParenthesisSelectContext -> {
                return SetOperatorTable(ctx.getRawText()).apply {
                    firstTable = queryExpressionNointo(ctx.queryExpressionNointo())
                    for (stmt in ctx.unionParenthesis()) {
                        val setTable = SetOperatorTable.SetTable(
                            stmt.ALL()?.let { SetOperator.UNION_ALL } ?: SetOperator.UNION_DISTINCT,
                            queryExpressionNointo(stmt.queryExpressionNointo())
                        )
                        this.addSetTable(setTable)
                    }
                    if (ctx.UNION() != null) {
                        val setTable = SetOperatorTable.SetTable(
                            ctx.ALL()?.let { SetOperator.UNION_ALL } ?: SetOperator.UNION_DISTINCT,
                            queryExpression(ctx.queryExpression())
                        )
                        this.addSetTable(setTable)
                    }
                    if (ctx.orderByClause() != null) {
                        this.orderBys = orderBys(ctx.orderByClause())
                    }
                }
            }
            else -> {
                throw WillNeverHappenException("not supposed to here")
            }
        }
    }

    private fun querySpecification(ctx: QuerySpecificationContext): SelectTable {
        val selectTable = generateSelectTable(ctx.fromClause(), ctx.selectElements(), ctx.orderByClause())
        if (ctx.selectSpec().any { it.DISTINCT() != null }) {
            selectTable.isDistinct = true
        }
        if (ctx.selectIntoExpression() != null) {
            //TODO()
        }
        return selectTable
    }

    private fun queryExpression(ctx: QueryExpressionContext): SelectTable {
        return if (ctx.querySpecification() != null) {
            querySpecification(ctx.querySpecification())
        } else {
            queryExpression(ctx.queryExpression())
        }
    }

    private fun querySpecificationNointo(ctx: QuerySpecificationNointoContext): SelectTable {
        return generateSelectTable(ctx.fromClause(), ctx.selectElements(), ctx.orderByClause()).apply {
            this.isDistinct = ctx.selectSpec().any { it.DISTINCT() != null }
        }
    }

    private fun queryExpressionNointo(ctx: QueryExpressionNointoContext): SelectTable {
        return if (ctx.queryExpressionNointo() != null) {
            queryExpressionNointo(ctx.queryExpressionNointo())
        } else {
            querySpecificationNointo(ctx.querySpecificationNointo())
        }
    }

    private fun generateSelectTable(
        from: FromClauseContext?,
        selects: SelectElementsContext,
        orderBys: OrderByClauseContext?
    ): SelectTable {
        return SelectTable((selects.parent as ParserRuleContext).getRawText()).apply {
            addFields(selectElements(selects))
            if (from != null) {
                this.from = from(from)
                where = where(from)
                groupBy = groupBy(from)
            }
            if (orderBys != null) {
                this.orderBys = orderBys(orderBys)
            }
        }
    }

    private fun from(from: FromClauseContext): FromItem {
        return tableSources(from.tableSources())
    }

    private fun where(from: FromClauseContext): SelectExpr? = from.whereExpr?.let { expression(it) }

    private fun groupBy(from: FromClauseContext): GroupByExpr {
        return GroupByExpr(from.groupByItem()?.map { expression(it.expression()) } ?: emptyList(),
            from.havingExpr?.let { expression(it) })
    }

    private fun orderBys(orderBy: OrderByClauseContext): List<OrderByItem> {
        return orderBy.orderByExpression()
            ?.map { ord ->
                ord.order?.let { OrderByItem(expression(ord.expression()), ord.DESC() != null) }
                    ?: OrderByItem(expression(ord.expression()))
            }
            ?: emptyList()
    }

    private fun tableSources(tableSourcesContext: TableSourcesContext): FromItem {
        fun joinPart(ctx: JoinPartContext): JoinTable.JoinPart {
            when (ctx) {
                is InnerJoinContext -> {
                    val joinType = ctx.INNER()?.let { JoinType.INNER } ?: JoinType.CROSS
                    return JoinTable.JoinPart(joinType,
                        tableSourceItem(ctx.tableSourceItem()),
                        ctx.expression()?.let { expression(it) }
                    )
                }
                is StraightJoinContext -> {
                    return JoinTable.JoinPart(
                        JoinType.STRAIGHT,
                        tableSourceItem(ctx.tableSourceItem()),
                        ctx.expression()?.let { expression(it) }
                    )
                }
                is OuterJoinContext -> {
                    return JoinTable.JoinPart(
                        ctx.LEFT()?.let { JoinType.LEFT } ?: JoinType.RIGHT,
                        tableSourceItem(ctx.tableSourceItem()),
                        ctx.expression()?.let { expression(it) }
                    )
                }
                is NaturalJoinContext -> {
                    return JoinTable.JoinPart(
                        ctx.LEFT()?.let { JoinType.NATURAL_LEFT }
                            ?: ctx.RIGHT()?.let { JoinType.NATURAL_RIGHT }
                            ?: JoinType.NATURAL,
                        tableSourceItem(ctx.tableSourceItem()),
                        null
                    )
                }
                else -> throw WillNeverHappenException()
            }
        }

        val tableList = mutableListOf<FromItem>()
        for (tableSourceContext in tableSourcesContext.tableSource()) {
            when (tableSourceContext) {
                is TableSourceBaseContext -> {
                    val firstTable = tableSourceItem(tableSourceContext.tableSourceItem())
                    if (tableSourceContext.joinPart()?.isNotEmpty() == true) {
                        val joinParts = mutableListOf<JoinTable.JoinPart>()
                        for (joinPartContext in tableSourceContext.joinPart()) {
                            joinParts.add(joinPart(joinPartContext))
                        }
                        tableList.add(JoinTable(firstTable, joinParts))
                    } else {
                        tableList.add(firstTable)
                    }
                }
                is TableSourceNestedContext -> {
                    val firstTable = tableSourceItem(tableSourceContext.tableSourceItem())
                    val joinParts = tableSourceContext.joinPart()?.map { joinPart(it) }?: emptyList()
                    if (joinParts.isEmpty()) {
                        tableList.add(firstTable)
                    } else {
                        tableList.add(JoinTable(firstTable, joinParts))
                    }
                }
                else -> throw WillNeverHappenException()
            }
        }
        return if (tableList.size == 1) {
            tableList[0]
        } else {
            JoinTable(tableList[0], tableList.drop(1).map { JoinTable.JoinPart(JoinType.COMMA, it, null) })
        }
    }

    private fun tableSourceItem(tableSourceItem: TableSourceItemContext): FromItem {
        when (tableSourceItem) {
            is AtomTableItemContext -> {
                val tableFullName = tableSourceItem.tableName().text
                val table = PhysicalTable(tableFullName)
                tableSourceItem.alias?.text?.let { table.alias = Alias(it) }
                return table
            }
            is SubqueryTableItemContext -> {
                val table = select(tableSourceItem.selectStatement())
                tableSourceItem.alias?.text?.let { table.alias = Alias(it) }
                return table
            }
            is TableSourcesItemContext -> return tableSources(tableSourceItem.tableSources())
            else -> throw WillNeverHappenException("will not come here")

        }
    }

    private fun selectElements(selects: SelectElementsContext): List<SelectExpr> {
        val fields = mutableListOf<SelectExpr>()
        if (selects.star != null) {
            fields.add(AsteriskField("*"))
        }
        for (selectElementContext in selects.selectElement()) {
            when (selectElementContext) {
                is SelectStarElementContext -> {
                    fields.add(AtomicField.of(selectElementContext.getRawText()))
                }
                is SelectColumnElementContext -> {
                    fields.add(AtomicField.of(selectElementContext.fullColumnName().getRawText()).apply {
                        if (selectElementContext.uid() != null) {
                            alias = Alias(selectElementContext.uid().getRawText(), selectElementContext.AS() != null)
                        }
                    })
                }
                is SelectFunctionElementContext -> {
                    fields.add(functionCall(selectElementContext.functionCall()).apply {
                        if (selectElementContext.uid() != null) {
                            alias = Alias(selectElementContext.uid().getRawText(), selectElementContext.AS() != null)
                        }
                    })
                }
                is SelectExpressionElementContext -> {
                    fields.add(expression(selectElementContext.expression()).apply {
                        if (selectElementContext.uid() != null) {
                            alias = Alias(selectElementContext.uid().getRawText(), selectElementContext.AS() != null)
                        }
                    })
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
                if (functionCall.specificFunction() is CaseFunctionCallContext) {
                    CaseField(functionCall.getRawText()).apply field@{
                        with(functionCall.specificFunction() as CaseFunctionCallContext) {
                            val caseExpr = expression()?.let { expression(it) }
                            val whenThenList = caseFuncAlternative().map {
                                CaseField.WhenThen(functionArg(it.condition), functionArg(it.consequent))
                            }
                            val elseExpr = elseArg?.let { functionArg(it) }
                            this@field.feed(caseExpr, whenThenList, elseExpr)
                        }
                    }
                } else {
                    FunctionField(functionCall.getRawText()).apply field@{
                        when (val function = functionCall.specificFunction()) {
                            is SimpleFunctionCallContext -> {
                                feed(
                                    (function.CURRENT_DATE() ?: function.CURRENT_TIME() ?: function.CURRENT_TIMESTAMP()
                                    ?: function.CURRENT_USER() ?: function.LOCALTIME()).text, emptyList()
                                )
                            }
                            is DataTypeFunctionCallContext -> {
                                feed(
                                    (function.CONVERT() ?: function.CAST()).text,
                                    expression(function.expression()),
                                    function.convertedDataType()?.let { KeywordField(it.getRawText()) })
                            }
                            is ValuesFunctionCallContext -> {
                                feed(function.VALUES().text, AtomicField.of(function.fullColumnName().getRawText()))
                            }
                            is CharFunctionCallContext -> {
                                val args = mutableListOf<SelectExpr>().apply {
                                    addAll(functionArgs(function.functionArgs()))
                                    if (function.USING() != null) {
                                        add(KeywordField(function.USING().text))
                                        add(ConstantField(function.charsetName().getRawText()))
                                    }
                                }
                                feed(function.CHAR().text, args.toList())
                            }
                            is PositionFunctionCallContext -> {
                                feed(function.POSITION().text,
                                    function.positionString?.let { ConstantField(it.text) }
                                        ?: function.positionExpression?.let { expression(it) },
                                    KeywordField(function.IN().text),
                                    function.inString?.let { ConstantField(it.text) }
                                        ?: function.inExpression?.let { expression(it) })
                            }
                            is SubstrFunctionCallContext -> {
                                feed((function.SUBSTR() ?: function.SUBSTRING()).text,
                                    function.sourceString?.let { ConstantField(it.text) }
                                        ?: function.sourceExpression.let { expression(it) },
                                    KeywordField(function.FROM().text),
                                    function.fromDecimal?.let { ConstantField(it.text) }
                                        ?: function.fromExpression?.let { expression(it) },
                                    function.FOR()?.let { KeywordField(it.text) },
                                    function.forDecimal?.let { ConstantField(it.text) }
                                        ?: function.forExpression?.let { expression(it) }
                                )
                            }
                            is TrimFunctionCallContext -> {
                                feed(function.TRIM().text,
                                    function.positioinForm?.let { KeywordField(it.text) },
                                    function.sourceString?.let { ConstantField(it.text) }
                                        ?: function.sourceExpression?.let { expression(it) },
                                    KeywordField(function.FROM().text),
                                    function.fromString?.let { ConstantField(it.text) }
                                        ?: function.fromExpression?.let { expression(it) }
                                )
                            }
                            is WeightFunctionCallContext -> {
                                feed(function.WEIGHT_STRING().text,
                                    function.stringLiteral()?.let { ConstantField(it.text) } ?: function.expression()
                                        ?.let { expression(it) },
                                    KeywordField(function.AS().text),
                                    KeywordField(function.stringFormat.text),
                                    function.decimalLiteral()?.let { ConstantField(it.text) },
                                    function.levelsInWeightString()?.let { KeywordField(it.text) }  //内部较复杂，但是我不关心
                                )
                            }
                            is ExtractFunctionCallContext -> {
                                feed(function.EXTRACT().text,
                                    KeywordField(function.intervalType().text),
                                    KeywordField(function.FROM().text),
                                    function.sourceString?.let { ConstantField(it.text) }
                                        ?: function.sourceExpression?.let { expression(it) }
                                )
                            }
                            is GetFormatFunctionCallContext -> {
                                feed(
                                    function.GET_FORMAT().text,
                                    KeywordField(function.datetimeFormat.text),
                                    ConstantField(function.stringLiteral().getRawText())
                                )
                            }
                        }
                    }
                }
            }
            is AggregateFunctionCallContext -> {
                FunctionField(functionCall.getRawText()).apply field@{
                    with(functionCall.aggregateWindowedFunction()) ctx@{
                        val funcName: TerminalNode?
                        when {
                            (AVG() ?: MAX() ?: MIN() ?: SUM()
                            ?: BIT_AND() ?: BIT_OR() ?: BIT_XOR()
                            ?: STD() ?: STDDEV() ?: STDDEV_POP() ?: STDDEV_SAMP()
                            ?: VAR_POP() ?: VAR_SAMP() ?: VARIANCE()
                                    ).also { funcName = it } != null -> {
                                this@field.feed(
                                    funcName!!.text,
                                    aggregator?.let { KeywordField(it.text) },
                                    functionArg(this.functionArg())
                                )
                            }
                            this.COUNT() != null -> {
                                if (starArg != null) {
                                    this@field.feed(COUNT().text, AsteriskField("*"))
                                } else {
                                    val args = mutableListOf<SelectExpr>().apply arg@{
                                        this@ctx.aggregator?.also { this@arg.add(KeywordField(it.text)) }
                                        this@ctx.functionArg()?.let { functionArg(this@ctx.functionArg()) }
                                            ?.also { this@arg.add(it) }
                                        this@arg.addAll(
                                            this@ctx.functionArgs()?.let { functionArgs(this@ctx.functionArgs()) }
                                                ?: emptyList()
                                        )
                                    }
                                    this@field.feed(COUNT().text, args.toList())
                                }
                            }
                            this.GROUP_CONCAT() != null -> {
                                val args = mutableListOf<SelectExpr>().apply arg@{
                                    this@ctx.aggregator?.also { this@arg.add(KeywordField(it.text)) }
                                    this@arg.addAll(functionArgs(this@ctx.functionArgs()))
                                    if (this@ctx.ORDER() != null) {
                                        this@arg.add(
                                            CompositeField(
                                                "ORDER BY" + this@ctx.orderByExpression()
                                                    .joinToString(",") { it.getRawText() })
                                                .feed(KeywordField("ORDER BY"))
                                                .feed(this@ctx.orderByExpression().map {
                                                    it.order?.run { OrderByItem(expression(it.expression())) }
                                                        ?: OrderByItem(expression(it.expression()), it.DESC() != null)
                                                })
                                        )
                                    }
                                    if (this@ctx.SEPARATOR() != null) {
                                        this@arg.add(KeywordField(this@ctx.SEPARATOR().text))
                                        this@arg.add(ConstantField(this@ctx.separator.text))
                                    }
                                }
                                this@field.feed(this.GROUP_CONCAT().text, args.toList())
                            }
                            else -> throw WillNeverHappenException()
                        }

                    }
                }
            }
            is ScalarFunctionCallContext -> {
                FunctionField(functionCall.getRawText()).feed(
                    functionCall.scalarFunctionName().getRawText(),
                    functionCall.functionArgs()?.let { functionArgs(it) })
            }
            is UdfFunctionCallContext -> {
                FunctionField(functionCall.getRawText()).feed(
                    functionCall.fullId().getRawText(),
                    functionCall.functionArgs()?.let { functionArgs(it) })
            }
            is PasswordFunctionCallContext -> {
                FunctionField(functionCall.getRawText()).apply {
                    with(functionCall.passwordFunctionClause()) {
                        this@apply.feed(functionName.text, functionArg(functionArg()))
                    }
                }
            }
            else -> throw WillNeverHappenException()
        }
    }

    private fun expression(expression: ExpressionContext): SelectExpr {
        return when (expression) {
            is NotExpressionContext -> {
                UnaryOperatorField(expression.getRawText()).feed(
                    expression(expression.expression()),
                    LogicalOperator.NOT
                )
            }
            is LogicalExpressionContext -> {
                BinaryOperatorField(expression.getRawText())
                    .feed(
                        expression(expression.expression(0)),
                        OP_MAP[expression.logicalOperator().text],
                        expression(expression.expression(1))
                    )
            }
            is IsExpressionContext -> {
                BinaryOperatorField(expression.getRawText())
                    .feed(
                        predicate(expression.predicate()),
                        expression.NOT()?.let { PredicateOperator.IS_NOT } ?: PredicateOperator.IS,
                        ConstantField(expression.testValue.text)
                    )
            }
            is PredicateExpressionContext -> predicate(expression.predicate())
            else -> throw WillNeverHappenException("will not come here")

        }
    }

    private fun expressionAtom(ctx: ExpressionAtomContext): SelectExpr {
        return when (ctx) {
            is ConstantExpressionAtomContext -> ConstantField(ctx.getRawText())
            is FullColumnNameExpressionAtomContext -> AtomicField.of(ctx.getRawText())
            is FunctionCallExpressionAtomContext -> functionCall(ctx.functionCall())
            is CollateExpressionAtomContext -> CompositeField(ctx.getRawText())
                .feed(
                    expressionAtom(ctx.expressionAtom()),
                    KeywordField(ctx.COLLATE().text),
                    ConstantField(ctx.collationName().getRawText())
                )
            is MysqlVariableExpressionAtomContext -> VariableField(ctx.getRawText())
            is UnaryExpressionAtomContext -> UnaryOperatorField(ctx.getRawText()).feed(
                expressionAtom(ctx.expressionAtom()),
                OP_MAP[ctx.unaryOperator().text]
            )
            is BinaryExpressionAtomContext -> CompositeField(ctx.getRawText())
                .feed(KeywordField(ctx.BINARY().text), expressionAtom(ctx.expressionAtom()))
            is NestedExpressionAtomContext -> CompositeField(ctx.getRawText(), true)
                .feed(ctx.expression().map { expression(it) })
            is NestedRowExpressionAtomContext -> FunctionField(ctx.getRawText())
                .feed(ctx.ROW().text, ctx.expression().map { expression(it) })
            is ExistsExpressionAtomContext -> FunctionField(ctx.getRawText())
                .feed(ctx.EXISTS().text, select(ctx.selectStatement()))
            is SubqueryExpressionAtomContext -> CompositeField(ctx.getRawText(), true)
                .feed(select(ctx.selectStatement()))
            is IntervalExpressionAtomContext -> IntervalField(ctx.getRawText())
                .feed(expression(ctx.expression()), IntervalQualifier.of(ctx.intervalType().getRawText()))
            is BitExpressionAtomContext -> BinaryOperatorField(ctx.getRawText())
                .feed(expressionAtom(ctx.left), OP_MAP[ctx.bitOperator().text], expressionAtom(ctx.right))
            is MathExpressionAtomContext -> BinaryOperatorField(ctx.getRawText())
                .feed(expressionAtom(ctx.left), OP_MAP[ctx.mathOperator().text], expressionAtom(ctx.right))
            is JsonExpressionAtomContext -> BinaryOperatorField(ctx.getRawText())
                .feed(expressionAtom(ctx.left), JsonOperator.of(ctx.jsonOperator().text), expressionAtom(ctx.right))
            else -> throw WillNeverHappenException()
        }
    }

    private fun predicate(ctx: PredicateContext): SelectExpr {
        return when (ctx) {
            is InPredicateContext -> MultiOperatorField(ctx.getRawText())
                .feed(predicate(ctx.predicate()),
                    ctx.NOT()?.let { PredicateOperator.NOT_IN } ?: PredicateOperator.IN,
                    ctx.selectStatement()?.let { listOf(select(it)) } ?: ctx.expressions().expression()
                        .map { expression(it) }
                )
            is IsNullPredicateContext -> BinaryOperatorField(ctx.getRawText())
                .feed(
                    predicate(ctx.predicate()),
                    ctx.nullNotnull().NOT()?.let { PredicateOperator.IS_NOT } ?: PredicateOperator.IS,
                    ConstantField((ctx.nullNotnull().NULL_LITERAL() ?: ctx.nullNotnull().NULL_SPEC_LITERAL())?.text)
                )
            is BinaryComparasionPredicateContext -> BinaryOperatorField(ctx.getRawText())
                .feed(predicate(ctx.left), OP_MAP[ctx.comparisonOperator().text], predicate(ctx.right))
            is SubqueryComparasionPredicateContext -> BinaryOperatorField(ctx.getRawText())
                .feed(
                    predicate(ctx.predicate()),
                    OP_MAP[ctx.comparisonOperator().text],
                    UnaryOperatorField(ctx.quantifier.text + " (" + ctx.selectStatement().getRawText() + " )")
                        .feed(select(ctx.selectStatement()), OP_MAP[ctx.quantifier.text])
                )
            is BetweenPredicateContext -> MultiOperatorField(ctx.getRawText())
                .feed(
                    predicate(ctx.predicate(0)),
                    ctx.NOT()?.let { PredicateOperator.NOT_BETWEEN } ?: PredicateOperator.BETWEEN,
                    listOf(predicate(ctx.predicate(1)), predicate(ctx.predicate(2)))
                )
            is SoundsLikePredicateContext -> BinaryOperatorField(ctx.getRawText())
                .feed(predicate(ctx.predicate(0)), PredicateOperator.SOUNDS_LIKE, predicate(ctx.predicate(1)))
            is LikePredicateContext -> BinaryOperatorField(ctx.getRawText())
                .feed(predicate(ctx.predicate(0)),
                    ctx.NOT()?.let { PredicateOperator.NOT_LIKE } ?: PredicateOperator.LIKE,
                    ctx.ESCAPE()?.let {
                        CompositeField(ctx.predicate(1).text + " " + ctx.ESCAPE().text + " " + ctx.STRING_LITERAL().text)
                            .feed(
                                predicate(ctx.predicate(1)),
                                KeywordField(ctx.ESCAPE().text),
                                ConstantField(ctx.STRING_LITERAL().text)
                            )
                    }
                        ?: predicate(ctx.predicate(1))
                )
            is RegexpPredicateContext -> BinaryOperatorField(ctx.getRawText())
                .feed(predicate(ctx.predicate(0)),
                    ctx.NOT()?.let { ctx.REGEXP()?.let { PredicateOperator.NOT_REGEXP } ?: PredicateOperator.NOT_RLIKE }
                        ?: ctx.REGEXP()?.let { PredicateOperator.REGEXP } ?: PredicateOperator.RLIKE,
                    predicate(ctx.predicate(1))
                )
            is ExpressionAtomPredicateContext -> expressionAtom(ctx.expressionAtom())
            is JsonMemberOfPredicateContext -> BinaryOperatorField(ctx.getRawText())
                .feed(predicate(ctx.predicate(0)), PredicateOperator.MEMBER_OF, predicate(ctx.predicate(1)))
            else -> throw WillNeverHappenException()
        }
    }

    private fun functionArgs(args: FunctionArgsContext): List<SelectExpr> {
        return args.functionArg().map { functionArg(it) }
    }

    private fun functionArg(arg: FunctionArgContext): SelectExpr {
        return when {
            arg.constant() != null -> ConstantField(arg.getRawText())
            arg.fullColumnName() != null -> AtomicField(arg.getRawText())
            arg.expression() != null -> expression(arg.expression())
            arg.functionCall() != null -> functionCall(arg.functionCall())
            else -> throw WillNeverHappenException()
        }
    }

    companion object {

        @JvmStatic
        val OP_MAP: Map<String, Operator> = mapOf(
            "&&" to LogicalOperator.AND,
            "AND" to LogicalOperator.AND,
            "||" to LogicalOperator.OR,
            "OR" to LogicalOperator.OR,
            "!" to LogicalOperator.NOT,
            "~" to LogicalOperator.NOT,
            "+" to CalculateOperator.PLUS,
            "-" to CalculateOperator.MINUS,
            "<<" to BitOperator.SHL,
            ">>" to BitOperator.SHR,
            "&" to BitOperator.AND,
            "^" to BitOperator.XOR,
            "|" to BitOperator.OR,
            "*" to CalculateOperator.MUL,
            "/" to CalculateOperator.DIVIDE,
            "%" to CalculateOperator.MOD,
            "DIV" to CalculateOperator.DIV,
            "MOD" to CalculateOperator.MOD,
            "--" to CalculateOperator.MINUSMINUS,
            "=" to PredicateOperator.EQUAL,
            ">" to PredicateOperator.GREATER,
            "<" to PredicateOperator.LESS,
            "<=" to PredicateOperator.LESS_EQUAL,
            ">=" to PredicateOperator.GREATER_EQUAL,
            "<>" to PredicateOperator.NOT_EQUAL,
            "!=" to PredicateOperator.NOT_EQUAL,
            "<=>" to PredicateOperator.EQUAL_NS,
            "ALL" to PredicateOperator.ALL,
            "ANY" to PredicateOperator.ANY,
            "SOME" to PredicateOperator.SOME,
        ).toMap(TreeMap<String, Operator>(String.CASE_INSENSITIVE_ORDER))

    }

}
