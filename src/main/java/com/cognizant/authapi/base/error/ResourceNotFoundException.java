package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 6/19/2019 6:58 PM
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String type, String name) {
        super(String.format("%s (%s) Not Found!", type, name));
    }

    public ResourceNotFoundException(String type, String fieldName, String fieldValue) {
        super(String.format("%s Not Found with %s : %s", type, fieldName, fieldValue));
    }
}
