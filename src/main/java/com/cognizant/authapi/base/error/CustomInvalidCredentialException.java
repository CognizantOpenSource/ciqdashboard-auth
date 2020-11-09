package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 7/23/2019 1:17 PM
 */
public class CustomInvalidCredentialException extends RuntimeException {
    public CustomInvalidCredentialException(String credential, String fieldName, String fieldValue) {
        super(String.format("Invalid %s with details %s : %s", credential, fieldName, fieldValue));
    }

    public CustomInvalidCredentialException(String message) {
        super(message);
    }
}
