package com.cognizant.authapi.users.services;

import com.cognizant.authapi.users.beans.TokenRequest;
import com.cognizant.authapi.users.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by 784420 on 7/22/2019 3:14 PM
 */
@Service
@Slf4j
@AllArgsConstructor
public class AuthService {

    TokenIdService tokenIdService;
    UserService userService;

    /**
     * Generate the token based on the user details provided
     * @param tokenRequest user details to generate token
     * @return token and expiry date with time details
     */
    public Map<String, Object> provideToken(TokenRequest tokenRequest) {
        return tokenIdService.provideToken(tokenRequest);
    }

    /**
     * Signing up or creating new user based on the Third party application's (Google, Microsoft etc..) token as of now Google implemented
     *
     * @param tokenRequest third party token which is generated and return from application
     * @return post signing up based on the token will return user detail which are stored in DB
     */
    public UserDTO getUser(TokenRequest tokenRequest) {
        return tokenIdService.getUser(tokenRequest);
    }
}
