package com.cognizant.authapi.users.services;

import com.cognizant.authapi.base.error.InvalidDetailsException;
import com.cognizant.authapi.base.error.UserNotFoundException;
import com.cognizant.authapi.base.services.JwtTokenService;
import com.cognizant.authapi.users.beans.TokenRequest;
import com.cognizant.authapi.users.beans.User;
import com.cognizant.authapi.users.dto.UserDTO;
import com.cognizant.authapi.users.repos.UserRepository;
import com.cognizant.authapi.users.util.AESUtil;
import com.cognizant.authapi.users.util.UserUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * Created by 784420 on 7/18/2019 11:50 AM
 */
@Service
@Slf4j
@AllArgsConstructor
public class TokenIdService {

//    private GoogleTokenValidatorService googleTokenValidatorService;
    private JwtTokenService jwtTokenService;
    private NativeUserService nativeUserService;

    private LDAPUserService ldapUserService;
    private UserRepository userRepository;
    private UserUtil userUtil;


    public Map<String, Object> provideToken(TokenRequest tokenRequest) {
        Map<String, Object> map;
        User user = null;
        String decryptPassword = AESUtil.decryptText(tokenRequest.getPassword());
        tokenRequest.setPassword(decryptPassword);
        switch (tokenRequest.getType()) {
            case "oauth2":
                user = validateProviderTokenId(tokenRequest);
                break;
            case "ldap":
                user = validateLDAPUserDetails(tokenRequest);
                break;
            case "Native":
                user = validateNativeUserDetails(tokenRequest);
                break;
            default:
                break;
        }

        map = jwtTokenService.generateToken(user);
        jwtTokenService.validateToken((String) map.get("auth_token"));

        return map;
    }

    private User validateLDAPUserDetails(TokenRequest tokenRequest) {
        return ldapUserService.validateUserDetails(tokenRequest);
    }
    private User validateNativeUserDetails(TokenRequest tokenRequest) {
        return nativeUserService.validateUserDetails(tokenRequest);
    }

    private User validateProviderTokenId(TokenRequest tokenRequest) {
        User googleUser = null;
        User dbUser;
        switch (tokenRequest.getProvider()) {
            case "google":
//                googleUser = googleTokenValidatorService.validateGoogleToken(tokenRequest.getIdToken());
                //log.info(googleUser.toString());
                break;
            case "microsoft":
                break;
            default:
                break;
        }

        if (null == googleUser) throw new InvalidDetailsException("Invalid Google token");

        Optional<User> user = userRepository.findByEmailIgnoreCase(googleUser.getEmail());
        if (user.isPresent()) {
            dbUser = user.get();
        } else {
            throw new UserNotFoundException(googleUser.getEmail());
        }

        return dbUser;
    }

    public UserDTO getUser(TokenRequest tokenRequest) {
        User googleUser = null;
        switch (tokenRequest.getProvider()) {
            case "google":
//                googleUser = googleTokenValidatorService.validateGoogleToken(tokenRequest.getIdToken());
                //log.info(googleUser.toString());
                break;
            case "microsoft":
                break;
            default:
                break;
        }
        return userUtil.convertToDto(googleUser);
    }
}
