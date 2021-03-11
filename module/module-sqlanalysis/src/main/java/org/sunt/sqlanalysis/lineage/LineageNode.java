package org.sunt.sqlanalysis.lineage;

import org.sunt.sqlanalysis.model.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LineageNode {

    private static final ThreadLocal<AtomicInteger> selectId = new ThreadLocal<>();

    static {
        selectId.set(new AtomicInteger(1));
    }

    private final SelectItem field;
    private final Table table;
    private final List<Map.Entry<RelationType, LineageNode>> relatedNodes = new LinkedList<>();

    private boolean possible = false;

    public LineageNode(SelectItem field, Table table) {
        this.field = field;
        this.table = table;
    }

    public SelectItem getField() {
        return field;
    }

    public Table getTable() {
        return table;
    }

    public RelationSource getRelationSource() {
        if (table instanceof SelectTable) {
            return RelationSource.SELECT_FROM;
        } else if (table instanceof InsertTable) {
            return RelationSource.INSERT_SELECT;
        } else if (table instanceof CreateTable) {
            if (table.getSourceTable().stream().allMatch(it->it instanceof PhysicalTable)){
                return RelationSource.CREATE_LIKE;
            }
            return RelationSource.CREATE_SELECT;
        } else if (table instanceof DeleteTable) {
            return RelationSource.DELETE_FROM;
        } else if (table instanceof UpdateTable) {
            return RelationSource.UPDATE_FROM;
        } else {
            return RelationSource.INSERT_SELECT;
        }
    }

    public boolean isAsteriskField() {
        return this.field instanceof AsteriskField;
    }

    public boolean isPossible() {
        return possible;
    }

    public ColumnName getColumnName() {
        Table table = this.table;
        if (table instanceof MultiInsertTable){
            table = table.getSourceTable().get(0);
        }
        String tableName = "";
        if (table instanceof SetOperatorTable) {
            tableName = "Set_Select-" + selectId.get().getAndIncrement();
        } else if (table instanceof SelectTable) {
            tableName = "Select-" + selectId.get().getAndIncrement();
        } else if (table instanceof PhysicalTable) {
            tableName = ((PhysicalTable) table).getTableName().getFullName();
        } else if (table instanceof FromItem) {
            tableName = ((FromItem) table).getLabel();
        } else if (table instanceof LandTable) {
            tableName = ((LandTable) table).getTableName().toString();
        }
        String columnName = field.getLabel();
        return new ColumnName(tableName + "." + columnName);
    }

    public List<Map.Entry<RelationType, LineageNode>> getRelatedNodes() {
        return relatedNodes;
    }

    public LineageNode addRelatedNode(RelationType relationType, LineageNode relatedNode) {
        this.relatedNodes.add(new AbstractMap.SimpleEntry<>(relationType, relatedNode));
        return this;
    }

    public LineageNode setPossible(boolean possible) {
        this.possible = possible;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineageNode)) return false;
        LineageNode that = (LineageNode) o;
        return Objects.equals(field, that.field) && Objects.equals(table, that.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, table);
    }
}
