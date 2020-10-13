package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 7/19/2019 2:43 PM
 */
public class InvalidResourceException extends RuntimeException {
    public InvalidResourceException(String resource, String fieldName, String fieldValue) {
        super(String.format("Invalid %s with details %s : %s", resource, fieldName, fieldValue));
    }
}
