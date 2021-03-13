package com.cognizant.authapi.external.service;

import com.cognizant.authapi.base.beans.JwtSecurityConstants;
import com.cognizant.authapi.base.services.JwtTokenService;
import com.cognizant.authapi.users.beans.Account;
import com.cognizant.authapi.users.beans.User;
import com.cognizant.authapi.users.beans.UserPrincipal;
import com.cognizant.authapi.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Map;

@Service
@AllArgsConstructor
public class ExternalTokenService {

    private JwtTokenService jwtTokenService;
    private UserService userService;

    public Map<String, Object> generateCurrentUserToken(User currentUser, int days) {
        Map<String, Object> map = jwtTokenService.generateToken(currentUser, days);
        map.put(JwtSecurityConstants.EXTERNAL_TOKEN, map.remove(JwtSecurityConstants.AUTH_TOKEN));
        map.put(JwtSecurityConstants.TOKEN_EXPIRES_AT, map.remove(JwtSecurityConstants.EXPIRES_AT));
        return map;
    }

    public Map<String, Object> generateTokenByRobot(@NotBlank String robotId, @NotBlank String robotName, int days) {
        return generateTokenByIdAndNameWithDays(robotId, robotName, days);
    }

    public Map<String, Object> generateTokenForDashboard(String userId, String username, int days) {
        return generateTokenByIdAndNameWithDays(userId, username, days);
    }

    private Map<String, Object> generateTokenByIdAndNameWithDays(String id, String name, int days){
        User user = generateUserByIdAndName(id, name);
        Map<String, Object> map = jwtTokenService.generateToken(user, days);
        map.put(JwtSecurityConstants.EXTERNAL_TOKEN, map.remove(JwtSecurityConstants.AUTH_TOKEN));
        map.put(JwtSecurityConstants.TOKEN_EXPIRES_AT, map.remove(JwtSecurityConstants.EXPIRES_AT));
        return map;
    }

    private User generateUserByIdAndName(@NotBlank String id, @NotBlank String name) {
        Account account = getAccount(id);
        return getUser(id, name, account);
    }

    private User getUser(@NotBlank String id, @NotBlank String name, Account account){
        User user = new User();
        user.setId(id);
        user.setFirstName(name);
        user.setLastName(name);
        user.setEmail(name+"@ciqdashboard-system.com");
        user.setAccount(account);
        user.setActive(true);
        return user;
    }

    private Account getAccount(@NotBlank String id){
        Account account = new Account();
        account.setId(id);
        account.setUserId(id);
        account.setOwnProjectIds(new ArrayList<>());
        account.setProjectIds(new ArrayList<>());
        account.setRoles(new ArrayList<>());
        return account;
    }

    public User getCurrentUser() {
        Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (auth instanceof UserPrincipal) {
            UserPrincipal userPrincipal = (UserPrincipal) auth;
            return userService.assertAndGetUser(userPrincipal.getId());
        } else {
            throw new AuthenticationCredentialsNotFoundException("user not signed in");
        }
    }
}
