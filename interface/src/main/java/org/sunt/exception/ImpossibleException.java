package org.sunt.exception;

public class ImpossibleException extends RuntimeException{

    public ImpossibleException() {
        super();
    }

    public ImpossibleException(String message) {
        super(message);
    }
}
