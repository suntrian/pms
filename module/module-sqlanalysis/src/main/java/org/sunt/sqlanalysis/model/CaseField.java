package org.sunt.sqlanalysis.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CaseField extends SelectExpr{

    private Expression caseExpr;

    private List<WhenThen> whenExpr;

    private Expression elseExpr;

    public CaseField(String expression) {
        super(expression);
    }

    public CaseField feed(Expression caseExpr, List<WhenThen> whenThenList, Expression elseExpr) {
        this.caseExpr = caseExpr;
        this.whenExpr = whenThenList;
        this.elseExpr = elseExpr;
        return this;
    }

    public Expression getCaseExpr() {
        return caseExpr;
    }

    public List<WhenThen> getWhenExpr() {
        return whenExpr == null? Collections.emptyList(): whenExpr;
    }

    public Expression getElseExpr() {
        return elseExpr;
    }

    @Override
    public CaseField clone() {
        return new CaseField(expression).feed(Optional.ofNullable(caseExpr).map(Expression::clone).orElse(null),
            whenExpr.stream().map(WhenThen::clone).collect(Collectors.toList()),
            Optional.ofNullable(elseExpr).map(Expression::clone).orElse(null)
                );
    }

    @Override
    public String getLabel() {
        String label = alias != null? alias.getAlias(): "";
        if ("".equals(label)) {
            if (elseExpr != null && elseExpr instanceof AtomicField) {
                label = ((AtomicField) elseExpr).getLabel();
            } else {
                for (WhenThen whenThen : whenExpr) {
                    if (whenThen.getThen() !=null && whenThen.getThen() instanceof AtomicField) {
                        label = ((AtomicField) whenThen.getThen()).getLabel();
                        break;
                    }
                }
            }
        }
        return label;
    }

    public static class WhenThen implements Cloneable{
        private final Expression when;
        private final Expression then;

        public WhenThen(Expression when, Expression then) {
            this.when = when;
            this.then = then;
        }

        public Expression getWhen() {
            return when;
        }

        public Expression getThen() {
            return then;
        }

        @Override
        protected WhenThen clone() {
            return new WhenThen(when.clone(), then.clone());
        }
    }
}
