package com.cognizant.authapi.users.services;

import com.cognizant.authapi.base.beans.LeapApiResponse;
import com.cognizant.authapi.base.error.CustomInvalidCredentialException;
import com.cognizant.authapi.base.error.UserNotFoundException;
import com.cognizant.authapi.users.beans.User;
import com.cognizant.authapi.users.beans.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by 784420 on 8/9/2019 6:55 PM
 */
@Service
@Slf4j
@AllArgsConstructor
public class PasswordService {

    private PasswordEncoder passwordEncoder;
    private UserService userService;
    //private PasswordAuditRepository repository;

    private static final String RESPONSE_TEMPLATE = "Password Changed Successfully. for User %s" ;

    public ResponseEntity changePassword(CharSequence newPassword, CharSequence oldPassword) {

        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<User> user = userService.getUserByEmail(principal.getEmail());
        if (!user.isPresent())
            throw new UserNotFoundException(principal.getEmail());
        if (passwordEncoder.matches(oldPassword, user.get().getPassword())) {
            user.get().setPassword(passwordEncoder.encode(newPassword));
            userService.updateUser(user.get());
        } else {
            throw new CustomInvalidCredentialException("Password", "UserName", principal.getEmail());
        }
        LeapApiResponse response = new LeapApiResponse(LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Success",
                String.format(RESPONSE_TEMPLATE, principal.getEmail())
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity resetPassword(String userEmailId, CharSequence password) {
        Optional<User> optionalUser = userService.getUserByEmail(userEmailId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(passwordEncoder.encode(password));
            userService.updateUser(user);
        } else {
            throw new UserNotFoundException(userEmailId);
        }

        LeapApiResponse response = new LeapApiResponse(LocalDateTime.now(),
                HttpStatus.OK.value(),
                "Success",
                String.format(RESPONSE_TEMPLATE, userEmailId)
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
