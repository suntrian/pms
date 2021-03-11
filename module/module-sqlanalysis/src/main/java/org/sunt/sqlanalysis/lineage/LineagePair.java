package org.sunt.sqlanalysis.lineage;

import org.sunt.sqlanalysis.model.ColumnName;

public class LineagePair {

    private final ColumnName source;
    private final ColumnName target;
    private final RelationType relationType;

    public LineagePair(ColumnName source, ColumnName target, RelationType relationType) {
        this.source = source;
        this.target = target;
        this.relationType = relationType;
    }

    public ColumnName getSource() {
        return source;
    }

    public ColumnName getTarget() {
        return target;
    }

    public RelationType getRelationType() {
        return relationType;
    }
}

