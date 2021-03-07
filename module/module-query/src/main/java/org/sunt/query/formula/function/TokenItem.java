package org.sunt.query.formula.function;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
                return text.isEmpty() ? COLUMN() : COLUMN(text);
            case CONSTANT:
                return CONSTANT(text);
            case FUNCTION:
                return text.isEmpty() ? FUNCTION() : FUNCTION(text);
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
        //checkText(text, "(", ")");
        return new TokenItem(TokenScope.PARENTHESES, text);
    }

    public static TokenItem BRACE(String text) {
        return new TokenItem(TokenScope.BRACE, text);
    }

    public static TokenItem SQUARE(String text) {
        return new TokenItem(TokenScope.SQUARE, text);
    }

    public static TokenItem CAL_OPERATOR(String text) {
        //checkText(text, "+", "-", "*", "/", "%");
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

    private static void checkText(String text, Set<String> options) {
        if (!options.contains(text)) {
            throw new IllegalArgumentException("错误的文本内容[" + text + "],要求为[" + String.join(",", options) + "]");
        }
    }

    private static void checkText(String text, String... options) {
        checkText(text, new HashSet<>(Arrays.asList(options)));
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
