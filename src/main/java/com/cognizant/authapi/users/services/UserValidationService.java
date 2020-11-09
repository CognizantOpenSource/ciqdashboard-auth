package com.cognizant.authapi.users.services;

import com.cognizant.authapi.users.beans.UserPrincipal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserValidationService {


    @Value("${app.permission.admin}")
    private String adminPermission;

    public boolean isAdmin() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(adminPermission);
        return userPrincipal.getAuthorities().contains(adminAuthority);
    }

    public UserPrincipal getLoggedInUser() {
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (auth instanceof UserPrincipal) {
            return (UserPrincipal) auth;
        } else {
            throw new AuthenticationCredentialsNotFoundException("user not signed in");
        }
    }

    public String getCurrentUserEmailId() {
        UserPrincipal loggedInUser = getLoggedInUser();
        return loggedInUser.getEmail();
    }

    public List<String> getCurrentUserProjectIds() {
        UserPrincipal loggedInUser = getLoggedInUser();
        return loggedInUser.getAccount().getProjectIds();
    }
}
