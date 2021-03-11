package org.sunt.sqlanalysis.model;

public class UnaryOperatorField extends SelectExpr {

    public UnaryOperatorField(String expression) {
        super(expression);
    }

    protected Expression field;

    protected Operator operator;

    @Override
    public DataType getDataType() {
        if (operator instanceof LogicalOperator) {
            return DataType.BOOL;
        } else if (operator instanceof CalculateOperator) {
            if (CalculateOperator.CONCAT == operator) {
                return DataType.STRING;
            }
            return DataType.DECIMAL;
        } else if (operator instanceof PredicateOperator) {
            return DataType.BOOL;
        } else if (operator instanceof BitOperator) {
            return DataType.DECIMAL;
        }
        return DataType.STRING;
    }

    public UnaryOperatorField feed(Expression field, Operator operator) {
        this.field = field;
        this.operator = operator;
        return this;
    }

    public Expression getField() {
        return field;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return operator.getSymbol() + " " + field;
    }

    @Override
    public UnaryOperatorField clone() {
        return new UnaryOperatorField(expression).feed(field.clone(), operator);
    }
}
