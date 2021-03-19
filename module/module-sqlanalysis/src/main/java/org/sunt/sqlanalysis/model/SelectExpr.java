package org.sunt.sqlanalysis.model;

public abstract class SelectExpr implements SelectItem{

    protected final String expression;

    protected int charStart = -1;
    protected int charStop = -1;
    protected int tokenStart = -1;
    protected int tokenStop = -1;

    protected Alias alias;

    protected DataType dataType;

    protected SelectExpr(String expression) {
        this.expression = expression;
    }

    @Override
    public String getExpression() {
        return expression;
    }

    @Override
    public Alias getAlias() {
        return alias != null? alias: Alias.EMPTY;
    }

    @Override
    public String getLabel() {
        return alias != null && !alias.isEmpty()? alias.getAlias(): expression;
    }

    public void setAlias(Alias alias) {
        this.alias = alias;
    }

    public DataType getDataType(){
        return this.dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return expression;
    }


    @Override
    public void setCharPos(int charStartIndex, int charStopIndex) {
        this.charStart = charStartIndex;
        this.charStop = charStopIndex;
    }

    @Override
    public void setTokenPos(int tokenStartIndex, int tokenStopIndex) {
        this.tokenStart = tokenStartIndex;
        this.tokenStop = tokenStopIndex;
    }

    @Override
    public int getCharStart() {
        return this.charStart;
    }

    @Override
    public int getCharStop() {
        return this.charStop;
    }

    @Override
    public int getTokenStart() {
        return this.tokenStart;
    }

    @Override
    public int getTokenStop() {
        return this.tokenStop;
    }

    @Override
    public abstract SelectExpr clone() ;
}
