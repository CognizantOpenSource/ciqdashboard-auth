package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 7/22/2019 5:28 PM
 */
public class StageExistsException extends ResourceExistsException {
    public StageExistsException(String stageName, String fieldName, String fieldValue) {
        super(stageName, fieldName, fieldValue);
    }
}
