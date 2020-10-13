package com.cognizant.authapi.users.util;

import com.cognizant.authapi.users.beans.Account;
import com.cognizant.authapi.users.beans.User;
import com.cognizant.authapi.users.dto.UserDTO;
import com.cognizant.authapi.users.dto.UserUpdateDTO;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by 784420 on 7/17/2019 7:51 PM
 */
@Component
public class UserUtil {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final String GOOGLE = "Google";

    public User convertPayloadToUser(GoogleIdToken.Payload payload) {
        User user = new User();
        user.setFirstName((String) payload.get("given_name"));
        user.setLastName((String) payload.get("family_name"));
        user.setEmail( payload.getEmail());
        user.setImage((String) payload.get("picture"));
        user.setOrg(GOOGLE);

        return user;
    }


    public UserDTO convertToDto(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    public User convertToNewEntity(UserDTO userDTO, Account account) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setCreatedDate(new Date());
        user.setAccount(account);
        if (userDTO.getPassword() != null)
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }

    public User convertToUpdateEntity(UserUpdateDTO userUpdateDTO, User user) {
        BeanUtils.copyProperties(userUpdateDTO, user);
        return user;
    }

    public UserUpdateDTO convertToUpdateDto(User updatedUser) {
        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        BeanUtils.copyProperties(updatedUser, userUpdateDTO);
        return userUpdateDTO;
    }
}
