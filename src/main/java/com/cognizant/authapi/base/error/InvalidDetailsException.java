package com.cognizant.authapi.base.error;

public class InvalidDetailsException extends RuntimeException {
    public InvalidDetailsException(String message) {
        super(String.format("Invalid details (%s)", message));
    }

    public InvalidDetailsException(String fieldName, String fieldValue, String message) {
        super(String.format("(%s:%s)Invalid details (%s)", fieldName, fieldValue, message));
    }
}
