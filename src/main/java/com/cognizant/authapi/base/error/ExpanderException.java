package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 6/19/2019 6:58 PM
 */
public class ExpanderException extends RuntimeException {
    public ExpanderException(Exception e) {
        super(e);
    }
}
