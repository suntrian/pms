package org.sunt.sqlanalysis.lineage;

import java.util.List;

public interface RequireMetaData {

    /**
     * 获取 catalog.schema.table 表下的顺序字段名称
     * @param catalog
     * @param schema
     * @param table
     * @return
     */
    List<String> getFields(String catalog, String schema, String table);


}
