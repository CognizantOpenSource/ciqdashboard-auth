package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 8/1/2019 2:42 PM
 */
public class UserSettingsExistsFoundException extends ResourceExistsException {
    public UserSettingsExistsFoundException(String fieldName, String fieldValue) {
        super("UserSettings", fieldName, fieldValue);
    }
}
