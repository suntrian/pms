package org.sunt.sqlanalysis.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SelectTable extends SelectExpr implements FromItem, AliasItem {

    private boolean distinct = false;

    private final List<SelectItem> fields = new LinkedList<>();

    private FromItem from;

    private Expression where;

    private GroupByExpr groupBy;

    private List<OrderByItem> orderBys;

    public SelectTable(String expression) {
        super(expression);
    }

    @Override
    public DataType getDataType() {
        return null;
    }

    public boolean isDistinct() {
        return distinct;
    }

    @Override
    public List<SelectItem> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public SelectTable addFields(List<SelectItem> fields) {
        this.fields.addAll(fields);
        return this;
    }

    public FromItem getFrom() {
        return from;
    }

    public Expression getWhere() {
        return where;
    }

    public GroupByExpr getGroupBy() {
        return groupBy;
    }

    public List<OrderByItem> getOrderBys() {
        return orderBys;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public void setFrom(FromItem from) {
        this.from = from;
    }

    public void setWhere(Expression where) {
        this.where = where;
    }

    public void setGroupBy(GroupByExpr groupBy) {
        this.groupBy = groupBy;
    }

    public void setOrderBys(List<OrderByItem> orderBys) {
        this.orderBys = orderBys;
    }

    @Override
    public String toString() {
        StringBuilder sqlBuilder = new StringBuilder("SELECT ").append(distinct?"DISTINCT ":"");
        if (!fields.isEmpty()) {
            for (SelectItem field : fields) {
                sqlBuilder.append(field)
                        .append(field.getAlias()!=null&&!field.getAlias().isEmpty()?" AS "+field.getAlias():"")
                        .append(", ");
            }
            sqlBuilder.setLength(sqlBuilder.length()-2);
        }
        if (from != null) {
            sqlBuilder.append(" FROM ");
            if (from instanceof SelectTable) {
                sqlBuilder.append("( ").append(from).append(" )");
            } else {
                sqlBuilder.append(from);
            }
            if (from instanceof AliasItem) {
                Alias alias = ((AliasItem)from).getAlias();
                if (alias!=null&&!alias.isEmpty()) {
                    sqlBuilder.append(" AS ").append(alias);
                }
            }
        }
        if (where != null) {
            sqlBuilder.append(" WHERE ").append(where);
        }
        if (groupBy != null) {
            sqlBuilder.append(" GROUP BY ").append(groupBy);
        }
        if (orderBys != null && !orderBys.isEmpty()) {
            sqlBuilder.append(" ORDER BY ");
            for (OrderByItem orderBy : orderBys) {
                sqlBuilder.append(orderBy).append(", ");
            }
            sqlBuilder.setLength(sqlBuilder.length()-2);
        }
        return sqlBuilder.toString();
    }

    @Override
    public List<FromItem> getSourceTable() {
        return Collections.singletonList(from);
    }

    @Override
    public SelectTable clone() {
        SelectTable clone = new SelectTable(expression);
        clone.setDistinct(distinct);
        clone.addFields(getFields().stream().map(SelectItem::clone).collect(Collectors.toList()));
        clone.setFrom(from);
        clone.setGroupBy(groupBy);
        clone.setWhere(where);
        clone.setOrderBys(orderBys);
        return clone;
    }
}
