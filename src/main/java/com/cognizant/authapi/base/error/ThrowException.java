package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 6/19/2019 6:58 PM
 */
public class ThrowException extends RuntimeException {
    public ThrowException(Exception e) {
        super(e);
    }
    public ThrowException(String message) {
        super(message);
    }

    public ThrowException(String message, Exception e) {
        super(message,e);
    }
}
