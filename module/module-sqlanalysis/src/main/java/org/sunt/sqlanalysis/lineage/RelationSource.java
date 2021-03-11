package org.sunt.sqlanalysis.lineage;

public enum RelationSource {

    SELECT_FROM,
    INSERT_SELECT,
    UPDATE_FROM,
    DELETE_FROM,
    MERGE_INTO,
    REPLACE_INTO,
    CREATE_SELECT,
    CREATE_LIKE,


}
