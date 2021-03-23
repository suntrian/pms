package org.sunt.sqlanalysis.model;

public enum PredicateOperator implements Operator{

    EQUAL("="),
    EQUAL_NS("<=>"),
    NOT_EQUAL("!="),
    LESS("<"),
    LESS_EQUAL("<="),
    GREATER(">"),
    GREATER_EQUAL(">="),

    IS("IS"),
    IS_NOT("IS NOT"),

    LIKE("LIKE"),
    NOT_LIKE("NOT LIKE"),
    RLIKE("RLIKE"),
    NOT_RLIKE("NOT RLIKE"),
    REGEXP("REGEXP"),
    NOT_REGEXP("NOT REGEXP"),

    IN("IN"),
    NOT_IN("NOT IN"),
    BETWEEN("BETWEEN"),
    NOT_BETWEEN("NOT BETWEEN"),
    LIKE_ANY("LIKE ANY"),
    NOT_LIKE_ANY("NOT LIKE ANY"),
    LIKE_ALL("LIKE ALL"),
    NOT_LIKE_ALL("NOT LIKE ALL"),
    SOUNDS_LIKE("SOUNDS LIKE"),

    EXISTS("EXISTS"),
    NOT_EXISTS("NOT EXISTS"),

    SOME("SOME"),
    ALL("ALL"),
    ANY("ANY"),

    IS_DISTINCT_FROM("IS DISTINCT FROM"),
    NOT_DISTINCT_FROM("IS NOT DISTINCT FROM"),

    MEMBER_OF("MEMBER OF"),
    SUBMULTISET_OF("SUBMULTISET OF");

    private final String symbol;

    PredicateOperator(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
