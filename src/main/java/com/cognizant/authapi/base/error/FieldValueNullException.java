package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 6/28/2019 4:52 PM
 */
public class FieldValueNullException extends RuntimeException {
    public FieldValueNullException(String message) {
        super(message);
    }
}
