package org.sunt.exception;

public class ResourceNotFoundException extends IllegalArgumentException {
    private static final long serialVersionUID = 1428607967207976636L;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
