package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 7/22/2019 5:28 PM
 */
public class UserExistsException extends ResourceExistsException {
    public UserExistsException(String fieldName, String fieldValue) {
        super("User", fieldName, fieldValue);
    }
}
