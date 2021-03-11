package org.sunt.sqlanalysis.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FunctionField extends SelectExpr {

    protected String functionName;

    protected List<Expression> params;

    public FunctionField(String expression) {
        super(expression);
    }

    public String getFunctionName() {
        return functionName;
    }

    public List<Expression> getParams() {
        return Collections.unmodifiableList(params);
    }

    @Override
    public DataType getDataType() {
        return null;
    }

    public FunctionField feed(String functionName, List<Expression> params) {
        this.functionName = functionName;
        this.params = params.stream().filter(Objects::nonNull).collect(Collectors.toList());
        return this;
    }

    public FunctionField feed(String functionName, Expression... params) {
        this.functionName = functionName;
        this.params = Arrays.stream(params).filter(Objects::nonNull).collect(Collectors.toList());
        return this;
    }

    public boolean isAggregate() {
        return Arrays.asList("COUNT", "SUM", "AVG", "MAX", "MIN", "GROUP_CONCAT", "STDEV", "STDEVP", "VAR", "VARP")
                .contains(functionName.toUpperCase());
    }

    @Override
    public FunctionField clone() {
        return new FunctionField(expression).feed(functionName, params.stream().map(Expression::clone).collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        StringBuilder sqlBuilder = new StringBuilder(functionName).append("(");
        if (params != null && !params.isEmpty()) {
            boolean formerKeyword = false;
            for (int i = 0; i < params.size(); i++) {
                if (i>0 && !formerKeyword) {
                    sqlBuilder.append(", ");
                }
                Expression param = params.get(i);
                sqlBuilder.append(param);
                formerKeyword = param instanceof KeywordField;
            }
        }
        sqlBuilder.append(")");
        return sqlBuilder.toString();
    }
}
