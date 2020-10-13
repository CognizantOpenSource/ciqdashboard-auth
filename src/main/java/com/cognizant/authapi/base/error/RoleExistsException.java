package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 7/22/2019 5:28 PM
 */
public class RoleExistsException extends ResourceExistsException {
    public RoleExistsException(String fieldName, String fieldValue) {
        super("Role", fieldName, fieldValue);
    }
}
