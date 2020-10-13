package com.cognizant.authapi.base.error;

/**
 * Created by 784420 on 7/19/2019 5:03 PM
 */
public class AccountNotFoundException extends ResourceNotFoundException {
    public AccountNotFoundException(String message) {
        super("Account", message);
    }
}
