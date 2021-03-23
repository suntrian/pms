package org.sunt.sqlanalysis.model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GroupByExpr implements Expression{

    private final List<SelectItem> fields;

    private final Expression having;

    public GroupByExpr(List<SelectItem> fields, Expression having) {
        this.fields = fields;
        this.having = having;
    }

    @Override
    public String toString() {
        StringBuilder sqlBuilder = new StringBuilder();
        if (fields == null || fields.isEmpty()) {
            sqlBuilder.append("()");
        } else {
            for (SelectItem field : fields) {
                sqlBuilder.append(field).append(',');
            }
            sqlBuilder.setLength(sqlBuilder.length()-1);
        }
        if (having!=null) {
            sqlBuilder.append(" HAVING ").append(having);
        }
        return sqlBuilder.toString();
    }

    public List<SelectItem> getFields() {
        return fields;
    }

    public Expression getHaving() {
        return having;
    }

    @Override
    public String getExpression() {
        return toString();
    }

    @Override
    public Expression clone() {
        return new GroupByExpr(this.fields.stream().map(SelectItem::clone).collect(Collectors.toList()), Optional.ofNullable(having).map(Expression::clone).orElse(null));
    }

    @Override
    public void setCharPos(int charStartIndex, int charStopIndex) {

    }

    @Override
    public void setTokenPos(int tokenStartIndex, int tokenStopIndex) {

    }

    @Override
    public int getCharStart() {
        return 0;
    }

    @Override
    public int getCharStop() {
        return 0;
    }

    @Override
    public int getTokenStart() {
        return 0;
    }

    @Override
    public int getTokenStop() {
        return 0;
    }
}
