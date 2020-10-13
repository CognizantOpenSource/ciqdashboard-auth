package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 6/20/2019 12:44 PM
 */
public class ProjectExistsExceptions extends ResourceExistsException {
    public ProjectExistsExceptions(String value) {
        super("Project", "ProjectName", value);
    }
}
