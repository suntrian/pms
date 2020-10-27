package org.sunt.sqlanalysis.lineage.listener

import org.sunt.sqlanalysis.exception.SqlParseException
import org.sunt.sqlanalysis.exception.WillNeverHappenException
import org.sunt.sqlanalysis.grammar.mysql.MySqlParser
import org.sunt.sqlanalysis.grammar.mysql.MySqlParserBaseListener
import org.sunt.sqlanalysis.lineage.model.Relation
import org.sunt.sqlanalysis.lineage.model.RelationType
import org.sunt.sqlanalysis.lineage.model.RelationType.CONDITION
import org.sunt.sqlanalysis.lineage.model.RelationType.DIRECTCOPY
import org.sunt.sqlanalysis.lineage.model.field.*
import org.sunt.sqlanalysis.lineage.model.table.*
import java.util.*

class MysqlLineageParserListener : MySqlParserBaseListener(), LineageListener {

    private val tables: MutableList<LogicalTable> = mutableListOf()

    private val variables: MutableList<VariableField> = mutableListOf()

    private var currentBlock: LogicalTable? = null

    override fun getTables(): List<LogicalTable> {
        return this.tables
    }

    override fun exitDmlStatement(ctx: MySqlParser.DmlStatementContext) {
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
                            this.tables.add(DeleteTable(table.alias))
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
                            this.tables.add(UpdateTable(table.alias))
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

    override fun exitDdlStatement(ctx: MySqlParser.DdlStatementContext) {
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


    override fun enterCreateProcedure(ctx: MySqlParser.CreateProcedureContext) {
        ctx.fullId()?.text?.let { procedureName -> currentBlock = AnonymousTable(procedureName) }
    }

    override fun exitCreateProcedure(ctx: MySqlParser.CreateProcedureContext) {
        this.currentBlock = null
    }

    override fun exitDeclareVariable(ctx: MySqlParser.DeclareVariableContext?) {
        for (uidContext in ctx?.uidList()?.uid() ?: emptyList()) {
            val variable = uidContext.text
            this.variables.add(VariableField(variable, if (currentBlock != null && currentBlock is AnonymousTable) currentBlock as AnonymousTable else AnonymousTable("")))
        }
    }

    private fun createTable(ctx: MySqlParser.CreateTableContext): CreateTable {
        return when (ctx) {
            is MySqlParser.CopyCreateTableContext -> {
                // create table like
                val tableName = ctx.tableName(0).text
                val createTable = CreateTable(parseTableName(tableName)).also {
                    it.temporary = (ctx.TEMPORARY() != null)
                }
                val likeTable = PhysicalTable(parseTableName(ctx.tableName(1).text))
                val createField = AsteriskField(createTable)
                val likeField = AllPhysicalField(likeTable)
                createField.getRelated().add(Relation(DIRECTCOPY, likeField))
                createTable
            }
            is MySqlParser.QueryCreateTableContext -> {
                // create table as select
                val tableName = ctx.tableName().text
                val createTable = CreateTable(parseTableName(tableName)).also {
                    it.temporary = ctx.TEMPORARY() != null
                    it.comment = ctx.tableOption().filterIsInstance(MySqlParser.TableOptionCommentContext::class.java)
                            .map { i -> i.STRING_LITERAL()?.text }.firstOrNull()
                }
                val selectTable = select(ctx.selectStatement())
                if (ctx.createDefinitions()?.createDefinition()?.isNotEmpty() == true) {
                    val createFields = createDefinitions(ctx.createDefinitions(), createTable)
                    if (createFields.size != selectTable.fields.size) {
                        //todo check asterisk field
                    } else {
                        for (i in createFields.indices) {
                            createFields[i].getRelated().add(Relation(DIRECTCOPY, selectTable.fields[i]))
                        }
                    }
                } else {
                    val createFields = AsteriskField(createTable)
                    createFields.getRelated().addAll(selectTable.fields.map { Relation(DIRECTCOPY, it) })
                }
                this.tables.add(createTable)
                createTable
            }
            is MySqlParser.ColumnCreateTableContext -> {
                val tableName = ctx.tableName().text
                val createTable = CreateTable(parseTableName(tableName)).also { it ->
                    with(it) {
                        isColumnCreateTable = true
                        temporary = ctx.TEMPORARY() != null
                        comment = ctx.tableOption().filterIsInstance(MySqlParser.TableOptionCommentContext::class.java)
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

    private fun createDefinitions(ctx: MySqlParser.CreateDefinitionsContext, createTable: CreateTable): List<CreateField> {
        val createFields = mutableListOf<CreateField>()
        val primaryKeys = mutableListOf<String>()
        val uniqueKeys = mutableListOf<String>()
        val foreignKeys = mutableListOf<String>()
        val indexKeys = mutableListOf<String>()
        for (createDefinition in ctx.createDefinition()) {
            when (createDefinition) {
                is MySqlParser.ColumnDeclarationContext -> {
                    val fieldName = createDefinition.uid().text
                    val createField = CreateField(fieldName, createTable)
                    with(createField) {
                        val dataTypeLengthPrecision = dataType(createDefinition.columnDefinition().dataType())
                        dataType = dataTypeLengthPrecision.first
                        dataLength = dataTypeLengthPrecision.second
                        dataPrecision = dataTypeLengthPrecision.third
                        for (columnConstraintContext in createDefinition.columnDefinition().columnConstraint()) {
                            when (columnConstraintContext) {
                                is MySqlParser.NullColumnConstraintContext -> nullable = columnConstraintContext.nullNotnull().NOT() == null
                                is MySqlParser.DefaultColumnConstraintContext -> default = columnConstraintContext.defaultValue().text
                                is MySqlParser.AutoIncrementColumnConstraintContext -> autoIncrement = columnConstraintContext.AUTO_INCREMENT() != null
                                is MySqlParser.PrimaryKeyColumnConstraintContext -> primaryKey = true
                                is MySqlParser.UniqueKeyColumnConstraintContext -> unique = true
                                is MySqlParser.CommentColumnConstraintContext -> comment = columnConstraintContext.STRING_LITERAL().text
                                is MySqlParser.ReferenceDefinitionContext -> {/* todo */
                                }
                            }
                        }

                    }
                    createFields.add(createField)
                }
                is MySqlParser.ConstraintDeclarationContext -> {
                    when (val tableConstraint = createDefinition.tableConstraint()) {
                        is MySqlParser.PrimaryKeyTableConstraintContext -> {
                            for (indexColumnNameContext in tableConstraint.indexColumnNames().indexColumnName()) {
                                primaryKeys.add(indexColumnNameContext.text)
                            }
                        }
                        is MySqlParser.UniqueKeyTableConstraintContext -> {
                            for (indexColumnNames in tableConstraint.indexColumnNames().indexColumnName()) {
                                primaryKeys.add(indexColumnNames.text)
                            }
                        }
                        is MySqlParser.ForeignKeyTableConstraintContext -> {
                            for (indexColumnNameContext in tableConstraint.indexColumnNames().indexColumnName()) {
                                foreignKeys.add(indexColumnNameContext.text)
                            }
                        }
                    }
                }
                is MySqlParser.IndexDeclarationContext -> {
                    when (val indexColumnDefinition = createDefinition.indexColumnDefinition()) {
                        is MySqlParser.SimpleIndexDeclarationContext -> {
                            for (indexColumnNameContext in indexColumnDefinition.indexColumnNames().indexColumnName()) {
                                indexKeys.add(indexColumnNameContext.text)
                            }
                        }
                        is MySqlParser.SpecialIndexDeclarationContext -> {
                            for (indexColumnNameContext in indexColumnDefinition.indexColumnNames().indexColumnName()) {
                                indexKeys.add(indexColumnNameContext.text)
                            }
                        }
                    }
                }
            }
        }
        for (field in createFields) {
            if (primaryKeys.contains(field.name)) {
                field.primaryKey = true
            }
            if (uniqueKeys.contains(field.name)) {
                field.unique = true
            }
            if (foreignKeys.contains(field.name)) {
                field.foreignKey = true
            }
            if (indexKeys.contains(field.name)) {
                field.indexKey = true
            }
        }
        return createFields
    }

    private fun dataType(ctx: MySqlParser.DataTypeContext): Triple<String, Int?, Int?> {
        return when (ctx) {
            is MySqlParser.StringDataTypeContext -> Triple(ctx.typeName.text, ctx.lengthOneDimension()?.decimalLiteral()?.text?.toInt(), null)
            is MySqlParser.NationalStringDataTypeContext -> Triple(ctx.typeName.text, ctx.lengthOneDimension()?.decimalLiteral()?.text?.toInt(), null)
            is MySqlParser.NationalVaryingStringDataTypeContext -> Triple(ctx.typeName.text, ctx.lengthOneDimension()?.decimalLiteral()?.text?.toInt(), null)
            is MySqlParser.DimensionDataTypeContext -> {
                val length = ctx.lengthOneDimension()?.decimalLiteral()?.text?.toInt() ?: ctx.lengthTwoDimension()?.decimalLiteral(0)?.text?.toInt()
                ?: ctx.lengthTwoOptionalDimension()?.decimalLiteral(0)?.text?.toInt()
                val precision = ctx.lengthTwoDimension()?.decimalLiteral(1)?.text?.toInt() ?: ctx.lengthTwoOptionalDimension()?.decimalLiteral()?.getOrNull(1)?.text?.toInt()
                Triple(ctx.typeName.text, length, precision)
            }
            is MySqlParser.SimpleDataTypeContext -> Triple(ctx.typeName.text, null, null)
            is MySqlParser.CollectionDataTypeContext -> Triple(ctx.typeName.text, null, null)
            is MySqlParser.SpatialDataTypeContext -> Triple(ctx.typeName.text, null, null)
            is MySqlParser.LongVarcharDataTypeContext -> Triple(ctx.typeName.text, null, null)
            is MySqlParser.LongVarbinaryDataTypeContext -> Triple(ctx.text, null, null)
            else -> throw WillNeverHappenException("")
        }
    }

    private fun select(ctx: MySqlParser.SelectStatementContext): SelectTable {
        var selectTableList: MutableList<SelectTable>?
        when (ctx) {
            is MySqlParser.SimpleSelectContext -> {
                return querySpecification(ctx.querySpecification())
            }
            is MySqlParser.ParenthesisSelectContext -> {
                return queryExpression(ctx.queryExpression())
            }
            is MySqlParser.UnionSelectContext -> {
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
            is MySqlParser.UnionParenthesisSelectContext -> {
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
        val unionTable = UnionTable(selectTableList)
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
            selectTableList.map { it.fields }.first().onEach { x -> unionField.getRelated().add(Relation(RelationType.DIRECTCOPY, x)) }
            return unionTable
        }

        for (i in 0 until fieldSize.iterator().next()) {
            val tempFields = LinkedList<LogicalField>()
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

    private fun querySpecification(ctx: MySqlParser.QuerySpecificationContext): SelectTable {
        return generateSelectTable(ctx.fromClause(), ctx.selectElements())
    }

    private fun queryExpression(ctx: MySqlParser.QueryExpressionContext): SelectTable {
        var sub = ctx
        while (sub.querySpecification() == null) {
            sub = sub.queryExpression()
        }
        return querySpecification(sub.querySpecification())
    }

    private fun querySpecificationNointo(ctx: MySqlParser.QuerySpecificationNointoContext): SelectTable {
        return generateSelectTable(ctx.fromClause(), ctx.selectElements())
    }

    private fun queryExpressionNointo(ctx: MySqlParser.QueryExpressionNointoContext): SelectTable {
        var sub = ctx
        while (sub.querySpecificationNointo() == null) {
            sub = sub.queryExpressionNointo()
        }
        return querySpecificationNointo(ctx.querySpecificationNointo())
    }

    private fun generateSelectTable(from: MySqlParser.FromClauseContext, selects: MySqlParser.SelectElementsContext): SelectTable {
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

    private fun from(from: MySqlParser.FromClauseContext): List<ITable> {
        return tableSources(from.tableSources())
    }

    private fun where(from: MySqlParser.FromClauseContext): List<Pair<RelationType, FullFieldName>> {
        if (from.WHERE() != null) {
            return expression(from.whereExpr).map { Pair(CONDITION, it.second) }
        }
        return emptyList()
    }

    private fun groupBy(from: MySqlParser.FromClauseContext): List<Pair<RelationType, FullFieldName>> {
        if (from.groupByItem() != null) {
            val fields = mutableListOf<Pair<RelationType, FullFieldName>>()
            for (groupByItemContext in from.groupByItem()) {
                fields.addAll(expression(groupByItemContext.expression()))
            }
            return fields
        }
        return emptyList()
    }

    private fun having(from: MySqlParser.FromClauseContext): List<Pair<RelationType, FullFieldName>> {
        if (from.havingExpr != null) {
            return expression(from.havingExpr).map { Pair(CONDITION, it.second) }
        }
        return emptyList()
    }

    private fun tableSources(tableSourcesContext: MySqlParser.TableSourcesContext): List<ITable> {
        val tableList = mutableListOf<ITable>()
        for (tableSourceContext in tableSourcesContext.tableSource()) {
            var joinPart: List<MySqlParser.JoinPartContext> = emptyList()
            when (tableSourceContext) {
                is MySqlParser.TableSourceBaseContext -> {
                    tableList.addAll(tableSourceItem(tableSourceContext.tableSourceItem()))
                    joinPart = tableSourceContext.joinPart()
                }
                is MySqlParser.TableSourceNestedContext -> {
                    tableList.addAll(tableSourceItem(tableSourceContext.tableSourceItem()))
                    joinPart = tableSourceContext.joinPart()
                }
            }
            for (joinPartContext in joinPart) {
                when (joinPartContext) {
                    is MySqlParser.InnerJoinContext -> {
                        tableList.addAll(tableSourceItem(joinPartContext.tableSourceItem()))
                    }
                    is MySqlParser.StraightJoinContext -> {
                        tableList.addAll(tableSourceItem(joinPartContext.tableSourceItem()))
                    }
                    is MySqlParser.OuterJoinContext -> {
                        tableList.addAll(tableSourceItem(joinPartContext.tableSourceItem()))
                    }
                    is MySqlParser.NaturalJoinContext -> {
                        tableList.addAll(tableSourceItem(joinPartContext.tableSourceItem()))
                    }
                }

            }
        }
        return tableList
    }

    private fun tableSourceItem(tableSourceItem: MySqlParser.TableSourceItemContext): List<ITable> {
        when (tableSourceItem) {
            is MySqlParser.AtomTableItemContext -> {
                val tableFullName = tableSourceItem.tableName().text
                val table = PhysicalTable(parseTableName(tableFullName))
                tableSourceItem.alias?.text?.let { table.alias = it }

                return Collections.singletonList(table)
            }
            is MySqlParser.SubqueryTableItemContext -> {
                val table = select(tableSourceItem.selectStatement())
                tableSourceItem.alias?.text?.let { table.alias = it }
                return Collections.singletonList(table)
            }
            is MySqlParser.TableSourcesItemContext -> {
                return tableSources(tableSourceItem.tableSources())
            }
            else -> {
                throw WillNeverHappenException("will not come here")
            }
        }
    }

    private fun selectElements(selects: MySqlParser.SelectElementsContext, selectTable: SelectTable): List<LogicalField> {
        val fields = mutableListOf<LogicalField>()
        if (selects.star != null) {
            fields.add(AsteriskField(selectTable))
        }
        for (selectElementContext in selects.selectElement()) {
            when (selectElementContext) {
                is MySqlParser.SelectStarElementContext -> {
                    //todo
                    fields.add(AsteriskField(selectTable))
                }
                is MySqlParser.SelectColumnElementContext -> {
                    val columnFullName = parseFieldName(selectElementContext.fullColumnName().text)
                    val selectField = SelectField(columnFullName.field, selectTable)
                    selectElementContext.uid()?.text?.let { selectField.alias = it }
                    fields.add(selectField)
                }
                is MySqlParser.SelectFunctionElementContext -> {
                    val expression = selectElementContext.functionCall().text
                    val selectField = SelectField(expression, selectTable)
                    selectElementContext.uid()?.text?.let { selectField.alias = it }

                    fields.add(selectField)
                    val innerFields = functionCall(selectElementContext.functionCall())
                    //todo
                }
                is MySqlParser.SelectExpressionElementContext -> {
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

    private fun functionCall(functionCall: MySqlParser.FunctionCallContext): List<Pair<RelationType, FullFieldName>> {
        return when (functionCall) {
            is MySqlParser.SpecificFunctionCallContext -> {
                when (val specificFunction = functionCall.specificFunction()) {
                    is MySqlParser.DataTypeFunctionCallContext -> expression(specificFunction.expression())
                    is MySqlParser.ValuesFunctionCallContext -> listOf(Pair(DIRECTCOPY, parseFieldName(specificFunction.fullColumnName().text)))
                    is MySqlParser.CaseFunctionCallContext -> {
                        val result = mutableListOf<Pair<RelationType, FullFieldName>>()
                        specificFunction.expression()?.let { result.addAll(expression(it)) }
                        for (caseFuncAlternativeContext in specificFunction.caseFuncAlternative()) {
                            result.addAll(functionArg(caseFuncAlternativeContext.condition))
                            result.addAll(functionArg(caseFuncAlternativeContext.consequent))
                        }
                        specificFunction.functionArg()?.let { result.addAll(functionArg(it)) }
                        return result
                    }
                    is MySqlParser.CharFunctionCallContext -> functionArgs(specificFunction.functionArgs())
                    is MySqlParser.PositionFunctionCallContext -> {
                        specificFunction.expression()?.flatMap { expression(it).asIterable() }?.toList().orEmpty()
                    }
                    is MySqlParser.SubstrFunctionCallContext -> {
                        specificFunction.expression()?.flatMap { expression(it).asIterable() }?.toList().orEmpty()
                    }
                    is MySqlParser.TrimFunctionCallContext -> {
                        specificFunction.expression()?.flatMap { expression(it).asIterable() }?.toList().orEmpty()
                    }
                    is MySqlParser.WeightFunctionCallContext -> specificFunction.expression()?.let { expression(it) }.orEmpty()
                    is MySqlParser.ExtractFunctionCallContext -> specificFunction.expression()?.let { expression(it) }.orEmpty()
                    is MySqlParser.GetFormatFunctionCallContext -> emptyList()
                    is MySqlParser.SimpleFunctionCallContext -> emptyList()
                    else -> emptyList()
                }
            }
            is MySqlParser.AggregateFunctionCallContext -> {
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
            is MySqlParser.ScalarFunctionCallContext -> functionCall.functionArgs()?.let { functionArgs(it) }.orEmpty()
            is MySqlParser.UdfFunctionCallContext -> functionCall.functionArgs()?.let { functionArgs(it) }.orEmpty()
            else -> emptyList()
        }
    }

    private fun expression(expression: MySqlParser.ExpressionContext): List<Pair<RelationType, FullFieldName>> {
        return when (expression) {
            is MySqlParser.NotExpressionContext -> expression(expression.expression())
            is MySqlParser.LogicalExpressionContext ->
                listOf(expression(expression.expression(0)), expression(expression.expression(1)))
                        .flatMap { it.asIterable() }
                        .toList()
            is MySqlParser.IsExpressionContext -> predicate(expression.predicate())
            is MySqlParser.PredicateExpressionContext -> predicate(expression.predicate())
            else -> throw WillNeverHappenException("will not come here")

        }
    }

    private fun expressionAtom(expression: MySqlParser.ExpressionAtomContext): List<Pair<RelationType, FullFieldName>> {
        return when (expression) {
            is MySqlParser.FullColumnNameExpressionAtomContext -> listOf(Pair(DIRECTCOPY, parseFieldName(expression.fullColumnName().text)))
            is MySqlParser.FunctionCallExpressionAtomContext -> functionCall(expression.functionCall())
            is MySqlParser.CollateExpressionAtomContext -> expressionAtom(expression.expressionAtom())
            is MySqlParser.UnaryExpressionAtomContext -> expressionAtom(expression.expressionAtom())
            is MySqlParser.BinaryExpressionAtomContext -> expressionAtom(expression.expressionAtom())
            is MySqlParser.NestedExpressionAtomContext -> expression.expression().flatMap { expression(it).asIterable() }.toList()
            is MySqlParser.NestedRowExpressionAtomContext -> expression.expression().flatMap { expression(it).asIterable() }.toList()
            is MySqlParser.SubqueryExpessionAtomContext -> emptyList()
            is MySqlParser.IntervalExpressionAtomContext -> expression(expression.expression())
            is MySqlParser.BitExpressionAtomContext -> listOf(expression.left, expression.right).flatMap { expressionAtom(it).asIterable() }.toList()
            is MySqlParser.MathExpressionAtomContext -> listOf(expression.left, expression.right).flatMap { expressionAtom(it).asIterable() }.toList()
            is MySqlParser.JsonExpressionAtomContext -> listOf(expression.left, expression.right).flatMap { expressionAtom(it).asIterable() }.toList()
            else -> emptyList()
        }
    }

    private fun predicate(predicate: MySqlParser.PredicateContext): List<Pair<RelationType, FullFieldName>> {
        return when (predicate) {
            is MySqlParser.InPredicateContext -> emptyList()
            is MySqlParser.IsNullPredicateContext -> emptyList()
            is MySqlParser.BinaryComparasionPredicateContext -> emptyList()
            is MySqlParser.SubqueryComparasionPredicateContext -> emptyList()
            is MySqlParser.BetweenPredicateContext -> emptyList()
            is MySqlParser.SoundsLikePredicateContext -> emptyList()
            is MySqlParser.LikePredicateContext -> emptyList()
            is MySqlParser.RegexpPredicateContext -> emptyList()
            is MySqlParser.ExpressionAtomPredicateContext -> expressionAtom(predicate.expressionAtom())
            is MySqlParser.JsonMemberOfPredicateContext -> emptyList()
            else -> emptyList()
        }
    }

    private fun functionArgs(args: MySqlParser.FunctionArgsContext): List<Pair<RelationType, FullFieldName>> {
        val result = LinkedList<Pair<RelationType, FullFieldName>>()
        if (args.fullColumnName() != null) {
            for (fullColumnNameContext in args.fullColumnName()) {
                result.add(Pair(DIRECTCOPY, parseFieldName(fullColumnNameContext.text)))
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

    private fun functionArg(arg: MySqlParser.FunctionArgContext): List<Pair<RelationType, FullFieldName>> {
        return when {
            arg.fullColumnName() != null -> listOf(Pair(DIRECTCOPY, parseFieldName(arg.fullColumnName().text)))
            arg.expression() != null -> expression(arg.expression())
            arg.functionCall() != null -> functionCall(arg.functionCall())
            else -> emptyList()
        }
    }

    companion object {

        fun unwrapId(id: String): String {
            if (id.startsWith('`') && id.endsWith('`')) {
                return id.substring(1, id.length - 1)
            }
            return id
        }

        fun parseFieldName(fullId: String): FullFieldName {
            val ids = fullId.split('.')
            return when (ids.size) {
                1 -> FullFieldName(null, null, null, unwrapId(ids[0]))
                2 -> FullFieldName(null, null, unwrapId(ids[0]), unwrapId(ids[1]))
                3 -> FullFieldName(null, unwrapId(ids[0]), unwrapId(ids[1]), unwrapId(ids[2]))
                4 -> FullFieldName(unwrapId(ids[0]), unwrapId(ids[1]), unwrapId(ids[2]), unwrapId(ids[3]))
                else -> throw WillNeverHappenException("")
            }
        }

        fun parseTableName(fullId: String): FullTableName {
            val ids = fullId.split('.')
            return when (ids.size) {
                1 -> FullTableName(null, null, unwrapId(ids[0]))
                2 -> FullTableName(null, unwrapId(ids[0]), unwrapId(ids[1]))
                3 -> FullTableName(unwrapId(ids[0]), unwrapId(ids[1]), unwrapId(ids[2]))
                else -> throw WillNeverHappenException("")
            }
        }

        fun qualifyTable(fullFieldName: FullFieldName, tables: Collection<ITable>): ITable {
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

}