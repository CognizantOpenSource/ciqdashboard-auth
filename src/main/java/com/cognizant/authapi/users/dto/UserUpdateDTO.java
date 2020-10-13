package com.cognizant.authapi.users.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by 784420 on 7/17/2019 3:41 PM
 */
@Data
public class UserUpdateDTO {
    private String firstName;
    private String lastName;
    private String image;
    @NotBlank(message = "Company/Organization Name should not be empty")
    @Size(min = 2, message = "Company/Organization Name should have at least 2 characters")
    private String org;
}
