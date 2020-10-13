package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 6/20/2019 12:18 PM
 */
public class ProjectNotFoundException extends ResourceNotFoundException {
    public ProjectNotFoundException(String message) {
        super("Project", message);
    }
}
