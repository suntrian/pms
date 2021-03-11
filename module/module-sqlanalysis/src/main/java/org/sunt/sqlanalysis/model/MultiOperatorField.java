package org.sunt.sqlanalysis.model;

import java.util.List;
import java.util.stream.Collectors;

public class MultiOperatorField extends UnaryOperatorField{

    private List<Expression> values;

    public MultiOperatorField(String expression) {
        super(expression);
    }

    public MultiOperatorField feed(Expression left, Operator op, List<Expression> values) {
        this.field = left;
        this.operator = op;
        this.values = values;
        return this;
    }

    public List<Expression> getValues() {
        return values;
    }

    @Override
    public String toString() {
        StringBuilder sqlBuilder = new StringBuilder(getField().toString());
        if (operator instanceof PredicateOperator) {
            switch ((PredicateOperator)operator) {
                case NOT_BETWEEN:
                    sqlBuilder.append(" NOT");
                case BETWEEN:
                    return sqlBuilder.append(" BETWEEN ").append(values.get(0)).append(" AND ").append(values.get(1)).toString();
                case NOT_IN:
                case NOT_LIKE_ALL:
                case NOT_LIKE_ANY:
                    sqlBuilder.append(" NOT ");
                case IN:
                case LIKE_ALL:
                case LIKE_ANY:
                    sqlBuilder.append(operator.getSymbol()).append(" (");
                    for (Expression value : values) {
                        sqlBuilder.append(value.toString()).append(",");
                    }
                    return sqlBuilder.replace(sqlBuilder.length()-1, sqlBuilder.length(), ")").toString();
            }
        }
        return sqlBuilder.toString();
    }

    @Override
    public MultiOperatorField clone() {
        return new MultiOperatorField(expression).feed(field.clone(), operator, values.stream().map(Expression::clone).collect(Collectors.toList()));
    }
}
