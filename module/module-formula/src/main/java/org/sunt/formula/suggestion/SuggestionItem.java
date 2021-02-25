package org.sunt.formula.suggestion;

import java.util.Objects;

public class SuggestionItem {

    private static final SuggestionItem NONE = new SuggestionItem(SuggestionScope.NONE, "");           //不推荐，输入常量或者数字或者完整匹配的时候
    private static final SuggestionItem COMMA = new SuggestionItem(SuggestionScope.COMMA, ",");          //逗号
    private static final SuggestionItem COLUMN = new SuggestionItem(SuggestionScope.COLUMN, null);          //空字段
    private static final SuggestionItem FUNCTION = new SuggestionItem(SuggestionScope.FUNCTION, null);      //空函数

    private final SuggestionScope scope;

    private final String text;

    private SuggestionItem(SuggestionScope scope, String text) {
        this.scope = scope;
        this.text = text;
    }

    public static SuggestionItem of(String scope, String text) {
        SuggestionScope suggestionScope = SuggestionScope.valueOf(scope.toUpperCase());
        switch (suggestionScope) {
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

    public static SuggestionItem RESERVED(String text) {
        return new SuggestionItem(SuggestionScope.RESERVED, text);
    }

    public static SuggestionItem PARENTHESES(String text) {
        return new SuggestionItem(SuggestionScope.PARENTHESES, text);
    }

    public static SuggestionItem BRACE(String text) {
        return new SuggestionItem(SuggestionScope.BRACE, text);
    }

    public static SuggestionItem CAL_OPERATOR(String text) {
        return new SuggestionItem(SuggestionScope.CAL_OPERATOR, text);
    }

    public static SuggestionItem CAL_OPERATOR() {
        return new SuggestionItem(SuggestionScope.CAL_OPERATOR, null);
    }

    public static SuggestionItem CMP_OPERATOR(String text) {
        return new SuggestionItem(SuggestionScope.CMP_OPERATOR, text);
    }

    public static SuggestionItem CMP_OPERATOR() {
        return new SuggestionItem(SuggestionScope.CMP_OPERATOR, null);
    }

    public static SuggestionItem LOG_OPERATOR(String text) {
        return new SuggestionItem(SuggestionScope.LOG_OPERATOR, text);
    }

    public static SuggestionItem FUNCTION(String text) {
        return new SuggestionItem(SuggestionScope.FUNCTION, text);
    }

    public static SuggestionItem FUNCTION() {
        return FUNCTION;
    }

    public static SuggestionItem COLUMN(String text) {
        return new SuggestionItem(SuggestionScope.COLUMN, text);
    }

    public static SuggestionItem COLUMN() {
        return COLUMN;
    }

    public static SuggestionItem NONE() {
        return NONE;
    }

    public static SuggestionItem COMMA() {
        return COMMA;
    }

    public static SuggestionItem CONSTANT(String constant) {
        return new SuggestionItem(SuggestionScope.CONSTANT, constant);
    }

    public SuggestionScope getScope() {
        return scope;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuggestionItem that = (SuggestionItem) o;
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
