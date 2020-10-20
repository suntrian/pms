package org.sunt.sqlanalysis.lineage.listener

import org.sunt.sqlanalysis.grammar.mysql.MySqlParser
import org.sunt.sqlanalysis.grammar.mysql.MySqlParserBaseListener
import org.sunt.sqlanalysis.lineage.model.field.AsteriskField
import org.sunt.sqlanalysis.lineage.model.field.LogicalField
import org.sunt.sqlanalysis.lineage.model.field.SelectField
import org.sunt.sqlanalysis.lineage.model.table.ITable
import org.sunt.sqlanalysis.lineage.model.table.LogicalTable
import org.sunt.sqlanalysis.lineage.model.table.PhysicalTable
import org.sunt.sqlanalysis.lineage.model.table.SelectTable
import java.util.*

class MysqlLineageParserListener: MySqlParserBaseListener() {

    private val tables: MutableList<LogicalTable> = LinkedList()

    override fun exitDmlStatement(ctx: MySqlParser.DmlStatementContext) {
        if (ctx.selectStatement() != null) {
            this.tables.add(select(ctx.selectStatement()))
        } else if (ctx.insertStatement() != null) {

        } else if (ctx.deleteStatement() != null) {

        } else if (ctx.updateStatement() != null) {

        } else if (ctx.replaceStatement() != null) {

        } else {

        }
    }

    override fun exitDdlStatement(ctx: MySqlParser.DdlStatementContext) {
        if (ctx.createTable() != null) {

        } else if (ctx.createView() != null) {

        } else if (ctx.createProcedure() != null) {

        } else if (ctx.createFunction() != null) {

        }
    }


    private fun select(ctx: MySqlParser.SelectStatementContext): SelectTable {
        return when (ctx) {
            is MySqlParser.SimpleSelectContext -> {
                querySpecification(ctx.querySpecification())
            }
            is MySqlParser.ParenthesisSelectContext -> {
                queryExpression(ctx.queryExpression())
            }
            is MySqlParser.UnionSelectContext -> {
                val selectTableList = mutableListOf<SelectTable>()
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
                //todo union table
                return selectTableList[0]
            }
            is MySqlParser.UnionParenthesisSelectContext -> {
                val selectTableList = mutableListOf<SelectTable>()
                selectTableList.add(queryExpressionNointo(ctx.queryExpressionNointo()))
                for (unionParenthesis in ctx.unionParenthesis()) {
                    if (unionParenthesis.queryExpressionNointo() != null) {
                        selectTableList.add(queryExpressionNointo(unionParenthesis.queryExpressionNointo()))
                    }
                }
                selectTableList.add(queryExpression(ctx.queryExpression()))
                //todo union table
                return selectTableList[0]
            }
            else -> {
                throw IllegalStateException("not supposed to here")
            }
        }
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
                //todo 拆分库 表名
                val table = PhysicalTable(tableFullName)
                val alias: String? = tableSourceItem.alias?.text
                //todo alias
                return Collections.singletonList(table)
            }
            is MySqlParser.SubqueryTableItemContext -> {
                val table = select(tableSourceItem.selectStatement())
                val alias: String? = tableSourceItem.alias?.text
                //todo alias
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
                    val alias: String? = selectElementContext.uid()?.text
                    val selectField = SelectField(columnFullName, selectTable)
                    fields.add(selectField)
                }
                is MySqlParser.SelectFunctionElementContext -> {
                    val expression = selectElementContext.functionCall().text
                    val alias: String? = selectElementContext.uid()?.text
                    val selectField = SelectField(expression, selectTable)
                    //todo
                    fields.add(selectField)
                }
                is MySqlParser.SelectExpressionElementContext -> {
                    val expression = selectElementContext.expression().text
                    val alias: String? = selectElementContext.uid()?.text
                    val selectField = SelectField(expression, selectTable)
                    //todo
                    fields.add(selectField)
                }
                else -> {
                    throw IllegalStateException("will not come here")
                }
            }
        }
        return fields
    }

}