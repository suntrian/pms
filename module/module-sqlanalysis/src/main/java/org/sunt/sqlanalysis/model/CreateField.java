package org.sunt.sqlanalysis.model;

public class CreateField extends SelectExpr {

    private String columnName;

    private String comment;

    private Integer dataLength;

    private Integer dataPrecision;

    private String defaultValue;

    private boolean nullable;

    private boolean autoIncrement;

    private boolean primaryKey;

    private boolean unique;

    private boolean foreignKey;

    private boolean indexKey;

    private boolean partitionField = false;

    public CreateField(String expression) {
        super(expression);
    }

    @Override
    public Alias getAlias() {
        return Alias.EMPTY;
    }

    public String getComment() {
        return comment;
    }

    public String getColumnName() {
        return columnName;
    }

    public boolean isPartitionField() {
        return partitionField;
    }

    public CreateField setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public CreateField setPartitionField(boolean partitionField) {
        this.partitionField = partitionField;
        return this;
    }

    public CreateField setColumnName(String columnName) {
        this.columnName = columnName;
        return this;
    }

    public Integer getDataLength() {
        return dataLength;
    }

    public void setDataLength(Integer dataLength) {
        this.dataLength = dataLength;
    }

    public Integer getDataPrecision() {
        return dataPrecision;
    }

    public void setDataPrecision(Integer dataPrecision) {
        this.dataPrecision = dataPrecision;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }

    public boolean isForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(boolean foreignKey) {
        this.foreignKey = foreignKey;
    }

    public boolean isIndexKey() {
        return indexKey;
    }

    public void setIndexKey(boolean indexKey) {
        this.indexKey = indexKey;
    }

    @Override
    public String toString() {
        return columnName + (dataType != null?" "+dataType:" ") + (comment!=null&&columnName!=null?" COMMENT '"+comment + "'": "");
    }

    @Override
    public CreateField clone() {
        CreateField field = new CreateField(expression)
                .setColumnName(columnName)
                .setPartitionField(partitionField)
                .setComment(comment);
        field.setDataType(dataType);
        return field;
    }
}
