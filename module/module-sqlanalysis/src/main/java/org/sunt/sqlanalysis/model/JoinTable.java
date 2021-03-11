package org.sunt.sqlanalysis.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class JoinTable implements FromItem {

    private final FromItem fromTable;

    private final List<JoinPart> joinParts;

    public JoinTable(FromItem fromTable, List<JoinPart> joinParts) {
        this.fromTable = fromTable;
        this.joinParts = joinParts;
    }

    @Override
    public List<SelectExpr> getFields() {
        return Collections.emptyList();
    }

    @Override
    public String getLabel() {
        return "";
    }

    public FromItem getFromTable() {
        return fromTable;
    }

    public List<JoinPart> getJoinParts() {
        return joinParts;
    }

    @Override
    public String toString() {
        StringBuilder sqlBuilder = new StringBuilder();
        if (fromTable instanceof SelectTable) {
            sqlBuilder.append("(\n").append(fromTable).append("\n )");
        } else {
            sqlBuilder.append(fromTable);
        }
        if (fromTable instanceof AliasItem) {
            Alias alias = ((AliasItem) fromTable).getAlias();
            if (alias!=null && !alias.isEmpty()) {
                sqlBuilder.append(" AS ").append(alias);
            }
        }

        for (JoinPart joinPart : joinParts) {
            sqlBuilder.append(" ").append(joinPart.getJoinType().getSymbol()).append(" ");
            if (joinPart.joinTable instanceof SelectTable) {
                sqlBuilder.append("(").append(joinPart.getJoinTable()).append(")");
            } else {
                sqlBuilder.append(joinPart.getJoinTable());
            }
            if (joinPart.getJoinTable() instanceof AliasItem) {
                Alias alias = ((AliasItem) joinPart.getJoinTable()).getAlias();
                if (alias != null && !alias.isEmpty()) {
                    sqlBuilder.append(" AS ").append(alias);
                }
            }
            if (joinPart.getOn() != null) {
                sqlBuilder.append(" ON ").append(joinPart.getOn());
            }
        }
        return sqlBuilder.toString();
    }

    @Override
    public List<FromItem> getSourceTable() {
        LinkedList<FromItem> sourceTables = new LinkedList<>();
        sourceTables.add(fromTable);
        sourceTables.addAll(joinParts.stream().map(JoinPart::getJoinTable).collect(Collectors.toList()));
        return sourceTables;
    }

    public static class JoinPart{

        private JoinType joinType;

        private FromItem joinTable;

        private Expression on;

        public JoinPart(JoinType joinType, FromItem joinTable, Expression on) {
            this.joinType = joinType;
            this.joinTable = joinTable;
            this.on = on;
        }

        public JoinType getJoinType() {
            return joinType;
        }

        public void setJoinType(JoinType joinType) {
            this.joinType = joinType;
        }

        public FromItem getJoinTable() {
            return joinTable;
        }

        public void setJoinTable(FromItem joinTable) {
            this.joinTable = joinTable;
        }

        public Expression getOn() {
            return on;
        }

        public void setOn(Expression on) {
            this.on = on;
        }
    }

}
