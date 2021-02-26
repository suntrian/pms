package org.sunt.formula.function;

import java.util.Objects;

public class TokenItem {

    private static final TokenItem NONE = new TokenItem(TokenScope.NONE, "");           //不推荐，输入常量或者数字或者完整匹配的时候
    private static final TokenItem COMMA = new TokenItem(TokenScope.COMMA, ",");          //逗号
    private static final TokenItem COLUMN = new TokenItem(TokenScope.COLUMN, null);          //空字段
    private static final TokenItem FUNCTION = new TokenItem(TokenScope.FUNCTION, null);      //空函数

    private final TokenScope scope;

    private final String text;

    private TokenItem(TokenScope scope, String text) {
        this.scope = scope;
        this.text = text;
    }

    public static TokenItem of(String scope, String text) {
        TokenScope tokenScope = TokenScope.valueOf(scope.toUpperCase());
        switch (tokenScope) {
            case COLUMN:
                return COLUMN(text);
            case CONSTANT:
                return CONSTANT(text);
            case FUNCTION:
                return FUNCTION(text);
            case NONE:
                return NONE();
            case COMMA:
                return COMMA();
            case BRACE:
                return BRACE(text);
            case RESERVED:
                return RESERVED(text);
            case PARENTHESES:
                return PARENTHESES(text);
            case CAL_OPERATOR:
                return CAL_OPERATOR(text);
            case CMP_OPERATOR:
                return CMP_OPERATOR(text);
            case LOG_OPERATOR:
                return LOG_OPERATOR(text);
            default:
                return NONE();
        }
    }

    public static TokenItem RESERVED(String text) {
        return new TokenItem(TokenScope.RESERVED, text);
    }

    public static TokenItem PARENTHESES(String text) {
        return new TokenItem(TokenScope.PARENTHESES, text);
    }

    public static TokenItem BRACE(String text) {
        return new TokenItem(TokenScope.BRACE, text);
    }

    public static TokenItem CAL_OPERATOR(String text) {
        return new TokenItem(TokenScope.CAL_OPERATOR, text);
    }

    public static TokenItem CAL_OPERATOR() {
        return new TokenItem(TokenScope.CAL_OPERATOR, null);
    }

    public static TokenItem CMP_OPERATOR(String text) {
        return new TokenItem(TokenScope.CMP_OPERATOR, text);
    }

    public static TokenItem CMP_OPERATOR() {
        return new TokenItem(TokenScope.CMP_OPERATOR, null);
    }

    public static TokenItem LOG_OPERATOR(String text) {
        return new TokenItem(TokenScope.LOG_OPERATOR, text);
    }

    public static TokenItem FUNCTION(String text) {
        return new TokenItem(TokenScope.FUNCTION, text);
    }

    public static TokenItem FUNCTION() {
        return FUNCTION;
    }

    public static TokenItem COLUMN(String text) {
        return new TokenItem(TokenScope.COLUMN, text);
    }

    public static TokenItem COLUMN() {
        return COLUMN;
    }

    public static TokenItem NONE() {
        return NONE;
    }

    public static TokenItem COMMA() {
        return COMMA;
    }

    public static TokenItem CONSTANT(String constant) {
        return new TokenItem(TokenScope.CONSTANT, constant);
    }

    public TokenScope getScope() {
        return scope;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenItem that = (TokenItem) o;
        return scope == that.scope &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scope, text);
    }

    @Override
    public String toString() {
        return scope.name() + (text == null ? "" : "('" + text + "')");
    }


}
