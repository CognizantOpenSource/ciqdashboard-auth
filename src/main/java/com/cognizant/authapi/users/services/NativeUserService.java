package com.cognizant.authapi.users.services;

import com.cognizant.authapi.base.error.CustomInvalidCredentialException;
import com.cognizant.authapi.base.error.UserNotFoundException;
import com.cognizant.authapi.users.beans.TokenRequest;
import com.cognizant.authapi.users.beans.User;
import com.cognizant.authapi.users.beans.UserLoginDetails;
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
    @Autowired
    private UserLoginDetailsService loginDetailsService;

    public User validateUserDetails(TokenRequest tokenRequest) {
        String username = tokenRequest.getUsername();
        Optional<User> optional = userService.getUserByEmailAndType(username,tokenRequest.getType());
        if (optional.isPresent()) {
            User user = optional.get();
            if (!user.isActive()) throw new CustomInvalidCredentialException(String.format("User account %s is disabled. Please contact administrator.", username));
            if (passwordEncoder.matches(tokenRequest.getPassword(), user.getPassword())) {
                loginDetailsService.save(new UserLoginDetails(user.getEmail()));
                return user;
            } else {
                Optional<UserLoginDetails> detailsOptional = loginDetailsService.get(username);
                detailsOptional.ifPresentOrElse(
                        userLoginDetails -> {
                            UserLoginDetails save = loginDetailsService.save(userLoginDetails.increaseFailureDetails());
                            if (save.getFailureCount().get() >= 5) {
                                user.setActive(false);
                                userService.updateUser(user);
                            }
                        },
                        () -> loginDetailsService.save(UserLoginDetails.getFirstFailure(username))
                );
                throw new CustomInvalidCredentialException("invalid email id/password");
            }
        } else {
            throw new UserNotFoundException(username);
        }
    }
}
