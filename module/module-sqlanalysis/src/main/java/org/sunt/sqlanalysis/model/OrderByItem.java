package org.sunt.sqlanalysis.model;

public class OrderByItem implements Expression{

    private final Expression field;

    private boolean asc = true;

    public OrderByItem(Expression field) {
        this.field = field;
    }

    public OrderByItem(Expression field, boolean asc) {
        this.field = field;
        this.asc = asc;
    }

    @Override
    public String toString() {
        return  field + (asc?" ASC ": " DESC ");
    }

    @Override
    public String getExpression() {
        return null;
    }

    @Override
    public OrderByItem clone() {
        return new OrderByItem(field.clone(), asc);
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
