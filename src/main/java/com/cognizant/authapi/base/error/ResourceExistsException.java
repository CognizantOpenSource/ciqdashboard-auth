package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 6/20/2019 12:41 PM
 */
public class ResourceExistsException extends RuntimeException {
    public ResourceExistsException(String resource, String field, String name) {
        super(String.format("%s, with %s : '%s' already exists", resource, field, name));
    }
}
