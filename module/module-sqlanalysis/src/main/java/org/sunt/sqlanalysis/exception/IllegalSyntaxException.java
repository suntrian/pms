package org.sunt.sqlanalysis.exception;

public class IllegalSyntaxException extends RuntimeException {

    private static final long serialVersionUID = -2156255838949930384L;

    public IllegalSyntaxException() {
    }

    public IllegalSyntaxException(String message) {
        super(message);
    }

}
