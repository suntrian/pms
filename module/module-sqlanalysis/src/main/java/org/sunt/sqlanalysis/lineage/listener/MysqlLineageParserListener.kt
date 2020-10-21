package org.sunt.sqlanalysis.lineage.listener

import org.sunt.sqlanalysis.grammar.mysql.MySqlParser
import org.sunt.sqlanalysis.grammar.mysql.MySqlParserBaseListener
import org.sunt.sqlanalysis.lineage.model.Relation
import org.sunt.sqlanalysis.lineage.model.RelationType
import org.sunt.sqlanalysis.lineage.model.field.AsteriskField
import org.sunt.sqlanalysis.lineage.model.field.LogicalField
import org.sunt.sqlanalysis.lineage.model.field.SelectField
import org.sunt.sqlanalysis.lineage.model.field.UnionField
import org.sunt.sqlanalysis.lineage.model.table.*
import java.util.*

class MysqlLineageParserListener : MySqlParserBaseListener(), LineageListener {

    private val tables: MutableList<LogicalTable> = LinkedList()

    override fun getTables(): List<LogicalTable> {
        return this.tables
    }

    override fun exitDmlStatement(ctx: MySqlParser.DmlStatementContext) {
        if (ctx.selectStatement() != null) {
            this.tables.add(select(ctx.selectStatement()))
        } else if (ctx.insertStatement() != null) {
            val tableName = ctx.insertStatement().tableName().text
            val insertTable = InsertTable(tableName)
            this.tables.add(insertTable)
        } else if (ctx.deleteStatement() != null) {
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
        } else if (ctx.updateStatement() != null) {
            val updateStatementContext = ctx.updateStatement()
            when {
                updateStatementContext.singleUpdateStatement() != null -> {
                    val tableName = updateStatementContext.singleUpdateStatement().tableName().text
                    this.tables.add(UpdateTable(tableName))
                }
                updateStatementContext.multipleUpdateStatement() != null -> {
                    val tables = tableSources(updateStatementContext.multipleUpdateStatement().tableSources())
                    for (table in tables) {
                        this.tables.add(UpdateTable(table.alias))
                    }
                }
            }
        } else if (ctx.replaceStatement() != null) {
            val tableName = ctx.replaceStatement().tableName().text
            this.tables.add(UpdateTable(tableName))
        }
    }

    override fun exitDdlStatement(ctx: MySqlParser.DdlStatementContext) {
        if (ctx.createTable() != null) {
            when (val createTableContext = ctx.createTable()) {
                is MySqlParser.CopyCreateTableContext -> {
                    val tableName = createTableContext.tableName(0).text
                    val createTable = CreateTable(tableName)
                    val field = AsteriskField(createTable)
                    this.tables.add(createTable)
                }
                is MySqlParser.QueryCreateTableContext -> {
                    val tableName = createTableContext.tableName().text
                    val createTable = CreateTable(tableName)
                    this.tables.add(createTable)
                }
                is MySqlParser.ColumnCreateTableContext -> {
                    val tableName = createTableContext.tableName().text
                    val createTable = CreateTable(tableName)
                    this.tables.add(createTable)
                }
            }
        } else if (ctx.createView() != null) {

        } else if (ctx.createProcedure() != null) {

        } else if (ctx.createFunction() != null) {

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
                throw IllegalStateException("not supposed to here")
            }
        }
        val unionTable = UnionTable(selectTableList)
        val fieldSize = selectTableList.map { it.fields }.filter { f -> f.none { x -> x is AsteriskField } }.map { it.size }.toSet()
        if (fieldSize.size > 1) {
            throw IllegalStateException("union查询的字段个数不一致")
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
        selectElements(selects, selectTable)
        return selectTable
    }

    private fun from(from: MySqlParser.FromClauseContext): List<ITable> {
        return tableSources(from.tableSources())
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
                val table = PhysicalTable(tableFullName)
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
                throw IllegalStateException("will not come here")
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
                    val columnFullName = selectElementContext.fullColumnName().text
                    val selectField = SelectField(columnFullName, selectTable)
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
                    throw IllegalStateException("will not come here")
                }
            }
        }
        return fields
    }

    private fun functionCall(functionCall: MySqlParser.FunctionCallContext): List<String> {
        return when (functionCall) {
            is MySqlParser.SpecificFunctionCallContext -> {
                when (val specificFunction = functionCall.specificFunction()) {
                    is MySqlParser.DataTypeFunctionCallContext -> expression(specificFunction.expression())
                    is MySqlParser.ValuesFunctionCallContext -> listOf(specificFunction.fullColumnName().text)
                    is MySqlParser.CaseFunctionCallContext -> {
                        val result = mutableListOf<String>()
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

    private fun expression(expression: MySqlParser.ExpressionContext): List<String> {
        return when (expression) {
            is MySqlParser.NotExpressionContext -> expression(expression.expression())
            is MySqlParser.LogicalExpressionContext ->
                listOf(expression(expression.expression(0)), expression(expression.expression(1)))
                        .flatMap { it.asIterable() }
                        .toList()
            is MySqlParser.IsExpressionContext -> predicate(expression.predicate())
            is MySqlParser.PredicateExpressionContext -> predicate(expression.predicate())
            else -> throw java.lang.IllegalStateException("will not come here")

        }
    }

    private fun expressionAtom(expression: MySqlParser.ExpressionAtomContext): List<String> {
        return when (expression) {
            is MySqlParser.FullColumnNameExpressionAtomContext -> listOf(expression.fullColumnName().text)
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

    private fun predicate(predicate: MySqlParser.PredicateContext): List<String> {
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

    private fun functionArgs(args: MySqlParser.FunctionArgsContext): List<String> {
        val result = LinkedList<String>()
        if (args.fullColumnName() != null) {
            for (fullColumnNameContext in args.fullColumnName()) {
                result.add(fullColumnNameContext.text)
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

    private fun functionArg(arg: MySqlParser.FunctionArgContext): List<String> {
        return when {
            arg.fullColumnName() != null -> listOf(arg.fullColumnName().text)
            arg.expression() != null -> expression(arg.expression())
            arg.functionCall() != null -> functionCall(arg.functionCall())
            else -> emptyList()
        }
    }

}