package com.cognizant.authapi.users.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 784420 on 8/1/2019 12:20 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserJenkinsDetails {
    private String host;
    private String userName;
    private String password;
}
