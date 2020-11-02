package com.cognizant.authapi.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class PasswordResetDto {

    @NotBlank(message = "Password should not be empty")
    @Size(min = 8, message = "Password should have at least 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$_@!%*#?&])[A-Za-z\\d$_@!%*#?&]{8,20}$",
            message = "Password should have minimum 8 and 20 characters, at least one uppercase letter, one lowercase letter, one number and one special character from $_@!%*#?&")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CharSequence password;
    @Email
    @NotBlank(message = "Email should not be empty")
    private String email;

    @JsonIgnore
    public CharSequence getPassword() {
        return password;
    }
}
