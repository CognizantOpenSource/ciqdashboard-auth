package com.cognizant.authapi.users.services;

import com.cognizant.authapi.base.error.CustomInvalidCredentialException;
import com.cognizant.authapi.base.error.UserNotFoundException;
import com.cognizant.authapi.users.beans.TokenRequest;
import com.cognizant.authapi.users.beans.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by 784420 on 7/23/2019 11:52 AM
 */
@Service
@Slf4j
public class NativeUserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    public User validateUserDetails(TokenRequest tokenRequest) {
        Optional<User> user = userService.getUserByEmail(tokenRequest.getUsername());
        if (user.isPresent()) {
            if (passwordEncoder.matches(tokenRequest.getPassword(), user.get().getPassword())) {
                return user.get();
            } else {
                throw new CustomInvalidCredentialException("Password", "UserName", tokenRequest.getUsername());
            }
        } else {
            throw new UserNotFoundException(tokenRequest.getUsername());
        }
    }
}
