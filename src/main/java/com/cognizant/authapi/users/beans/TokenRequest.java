package com.cognizant.authapi.users.beans;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by 784420 on 7/19/2019 3:21 PM
 */
@Data
public class TokenRequest {
    @NotBlank(message = "Type should not be empty")
    private String type;
    private String provider;
    private String username;
    private String password;
    private String idToken;
}
