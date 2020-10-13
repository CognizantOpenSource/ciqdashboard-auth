package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 7/18/2019 3:48 PM
 */
public class InvalidValueException extends RuntimeException {
    public InvalidValueException(String value, Throwable e) {
        super("Invalid "+value, e);
    }
    public InvalidValueException(String value) {
        super("Invalid "+value);
    }
}
