package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 8/1/2019 12:36 PM
 */
public class UserSettingsNotFoundException extends ResourceNotFoundException {
    public UserSettingsNotFoundException(String message) {
        super("UserSettings", message);
    }
}
