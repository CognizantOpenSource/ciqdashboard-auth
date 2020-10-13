package com.cognizant.authapi.users.dto;

import com.cognizant.authapi.users.beans.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by 784420 on 7/17/2019 3:41 PM
 */
@Data
public class UserDTO {
    private String id;

    private String firstName;
    private String lastName;
    @NotBlank(message = "Password should not be empty")
    @Size(min = 8, message = "Password should have at least 8 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CharSequence password;
    @Email
    @NotBlank(message = "Email should not be empty")
    private String email;
    private String image;
    @NotBlank(message = "Company/Organization Name should not be empty")
    @Size(min = 2, message = "Company/Organization Name should have at least 2 characters")
    private String org;
    private Account account;
    private boolean active;

    @JsonIgnore
    public CharSequence getPassword() {
        return password;
    }
}
