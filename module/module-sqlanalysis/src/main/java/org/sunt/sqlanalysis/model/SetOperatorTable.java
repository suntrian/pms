package org.sunt.sqlanalysis.model;

import org.sunt.sqlanalysis.exception.IllegalSyntaxException;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SetOperatorTable extends SelectTable{

    private SelectTable firstTable;

    private final List<SetTable> otherTables = new LinkedList<>();

    public SetOperatorTable(String expression) {
        super(expression);
    }

    public static class SetTable{
        private final SetOperator setOperator;
        private final SelectTable setTable;

        public SetTable(SetOperator setOperator, SelectTable setTable) {
            this.setOperator = setOperator;
            this.setTable = setTable;
        }

        public SetOperator getSetOperator() {
            return setOperator;
        }

        public SelectTable getSetTable() {
            return setTable;
        }
    }

    public SetOperatorTable setFirstTable(SelectTable firstTable) {
        this.firstTable = firstTable;
        validate();
        return this;
    }

    public SelectTable getFirstTable() {
        return firstTable;
    }

    public List<SetTable> getOtherTables() {
        return otherTables;
    }

    public SetOperatorTable addSetTable(SetTable setTable) {
        this.otherTables.add(setTable);
        validate();
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append(firstTable);
        for (SetTable otherTable : otherTables) {
            sqlBuilder.append(" ")
                    .append(otherTable.setOperator.getSymbol())
                    .append(" ")
                    .append(otherTable.getSetTable());
        }
        if (getOrderBys() != null && !getOrderBys().isEmpty()) {
            sqlBuilder.append(" ORDER BY ");
            for (OrderByItem orderBy : getOrderBys()) {
                sqlBuilder.append(orderBy).append(", ");
            }
            sqlBuilder.setLength(sqlBuilder.length()-2);
        }
        return sqlBuilder.toString();
    }

    private void validate() {
        if (this.firstTable !=null && !this.otherTables.isEmpty()) {
            if (this.firstTable.getFields().stream().anyMatch(x->x instanceof AsteriskField)) {
                return;
            }
            int fieldSize = this.firstTable.getFields().size();
            List<String> fieldName = this.firstTable.getFields().stream().map(SelectItem::getLabel).collect(Collectors.toList());
            for (SetTable otherTable : this.otherTables) {
                if (otherTable.getSetTable().getFields().stream().anyMatch(x->x instanceof AsteriskField)) {
                    continue;
                }
                if (otherTable.getSetTable().getFields().size() != fieldSize) {
                    throw new IllegalSyntaxException(otherTable.setOperator.getSymbol() + "查询的Select字段个数不一致");
                }
//                List<SelectItem> fields = otherTable.getSetTable().getFields();
//                for (int i = 0; i < fields.size(); i++) {
//                    SelectItem field = fields.get(i);
//                    if (!field.getLabel().equalsIgnoreCase(fieldName.get(i))){
//                        throw new IllegalSyntaxException(otherTable.setOperator.getSymbol() + "集合表的第"+(i+1)+"个字段名称不匹配:" + fieldName.get(i) + " <- " + field.getLabel());
//                    }
//                }
            }
        }
    }

    @Override
    public SetOperatorTable clone() {
        SetOperatorTable clone = new SetOperatorTable(expression).setFirstTable(firstTable.clone());
        for (SetTable otherTable : otherTables) {
            clone.addSetTable(new SetTable(otherTable.getSetOperator(), otherTable.getSetTable().clone()));
        }
        if (this.getOrderBys()!=null) {
            clone.setOrderBys(this.getOrderBys().stream().map(OrderByItem::clone).collect(Collectors.toList()));
        }
        return clone;
    }
}
