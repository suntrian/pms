package org.sunt.formula;

import org.antlr.v4.runtime.ParserRuleContext;
import org.sunt.formula.define.DataType;
import org.sunt.formula.define.IColumn;
import org.sunt.formula.define.SqlProduct;
import org.sunt.formula.exception.DataTypeMismatchException;
import org.sunt.formula.parser.FormulaBaseVisitor;
import org.sunt.formula.parser.FormulaParser;
import org.sunt.formula.suggestion.SuggestionScope;
import org.sunt.formula.suggestion.TokenStatus;

import java.util.function.Function;

public abstract class AbstractFormulaVisitor extends FormulaBaseVisitor<StatementInfo> {

    private final SqlProduct dialect;
    private final Function<String, IColumn> getColumnById;
    private final Function<String, IColumn> getColumnByName;

    public AbstractFormulaVisitor(SqlProduct dialect, Function<String, IColumn> getColumnById, Function<String, IColumn> getColumnByName) {
        this.dialect = dialect;
        this.getColumnById = getColumnById;
        this.getColumnByName = getColumnByName;
    }

    public static Boolean checkDataType(StatementInfo stmt, DataType expectedType) {
        if (expectedType == stmt.getDataType()) {
            return true;
        }
        throw new DataTypeMismatchException(stmt.getExpression(), expectedType, stmt.getDataType());
    }

    public StatementInfo visitConstant(FormulaParser.ConstantContext ctx) {
        StatementInfo stmt = new StatementInfo(ctx);
        if (ctx.BOOL() != null) {
            stmt.setDataType(DataType.BOOLEAN);
        } else if (ctx.INTEGER() != null) {
            stmt.setDataType(DataType.INTEGER);
        } else if (ctx.FLOAT() != null) {
            stmt.setDataType(DataType.DECIMAL);
        } else if (ctx.STRING() != null) {
            stmt.setDataType(DataType.STRING);
        } else if (ctx.NULL() != null) {
            stmt.setDataType(DataType.ANY);
        }
        stmt.setExpression(ctx.getText());
        stmt.setScope(SuggestionScope.CONSTANT(stmt.getExpression()));
        stmt.setStatus(TokenStatus.NORMAL);
        return stmt;
    }

    @Override
    public StatementInfo visitColumnId(FormulaParser.ColumnIdContext ctx) {
        return getColumn(ctx, ctx.getText());
    }

    @Override
    public StatementInfo visitColumnName(FormulaParser.ColumnNameContext ctx) {
        return getColumn(ctx, ctx.getText());
    }

    @Override
    public StatementInfo visitIdentity(FormulaParser.IdentityContext ctx) {
        return getColumn(ctx, ctx.getText());
    }


    private StatementInfo getColumn(ParserRuleContext ctx, String columnIdOrName) {
        IColumn column;
        if (columnIdOrName.startsWith("#")) {
            column = getColumnById.apply(columnIdOrName.substring(1));
        } else if (columnIdOrName.startsWith("`") && columnIdOrName.endsWith("`")) {
            column = getColumnByName.apply(columnIdOrName.substring(1, columnIdOrName.length() - 1));
        } else {
            column = getColumnByName.apply(columnIdOrName);
        }

        StatementInfo colStmt = new StatementInfo(ctx);
        colStmt.setScope(SuggestionScope.COLUMN(columnIdOrName));
        if (column == null) {
            colStmt.setStatus(TokenStatus.UNKNOWN);
            colStmt.setExpression(columnIdOrName);
            colStmt.setDataType(DataType.NONE);
        } else {
            colStmt.setStatus(TokenStatus.NORMAL);
            colStmt.setDataType(column.getDataType());
            colStmt.setExpression(column.getExpression());
        }

        return colStmt;
    }

}
