package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 7/15/2019 3:25 PM
 */
public class JobExistsException extends RuntimeException {
    public JobExistsException(String s) {
        super(s);
    }
}
