package org.finance.error;

import java.util.Map;

public class InvalidObjectException extends RuntimeException{
    private final Map<String, String> errors;

    public InvalidObjectException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
}
