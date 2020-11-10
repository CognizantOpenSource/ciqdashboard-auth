package com.cognizant.authapi.users.controllers;

import com.cognizant.authapi.users.dto.ChangePasswordDto;
import com.cognizant.authapi.users.dto.PasswordResetDto;
import com.cognizant.authapi.users.services.PasswordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity changePassword(@Valid @RequestBody ChangePasswordDto data) {
        return passwordService.changePassword(data.getNewPassword(), data.getOldPassword());
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/reset")
    @PreAuthorize("hasPermission('Password','leap.permission.admin')")
    public ResponseEntity resetPassword(@Valid @RequestBody PasswordResetDto data) {
        return passwordService.resetPassword(data.getEmail(), data.getPassword());
    }
}
