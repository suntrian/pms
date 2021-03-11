package org.sunt.sqlanalysis.model;

public class BinaryOperatorField extends UnaryOperatorField{

    private Expression right;

    public Expression getLeft() {
        return getField();
    }

    public BinaryOperatorField(String expression) {
        super(expression);
    }

    public BinaryOperatorField feed(Expression left, Operator op, Expression right) {
        this.field = left;
        this.operator = op;
        this.right = right;
        return this;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public String toString() {
        return getLeft().toString() + " " + operator.getSymbol() + " " + right.toString();
    }

    @Override
    public BinaryOperatorField clone() {
        return new BinaryOperatorField(expression).feed(getLeft().clone(), operator, getRight().clone());
    }
}
