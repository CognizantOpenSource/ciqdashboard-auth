package com.cognizant.authapi.users.controllers;

import com.cognizant.authapi.users.services.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 784420 on 8/9/2019 6:45 PM
 */
@Slf4j
@RestController
@RequestMapping("/users/password")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/update")
    public ResponseEntity changePassword(@RequestParam("newPassword") CharSequence newPassword,
                                         @RequestParam("oldPassword") CharSequence oldPassword) {
        return passwordService.changePassword(newPassword, oldPassword);
    }
}
