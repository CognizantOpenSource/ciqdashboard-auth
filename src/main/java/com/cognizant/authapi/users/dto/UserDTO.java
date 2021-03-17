/*
 *  © [2021] Cognizant. All rights reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.cognizant.authapi.users.dto;

import com.cognizant.authapi.users.beans.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *UserDTO
 *
 * @author Cognizant
 */
@Data
public class UserDTO {
    private String id;

    private String firstName;
    private String lastName;
    @NotBlank(message = "Password should not be empty")
    @Size(min = 8, message = "Password should have at least 8 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$_@!%*#?&])[A-Za-z\\d$_@!%*#?&]{8,20}$",
            message = "Password should have minimum 8 and 20 characters, at least one uppercase letter, one lowercase letter, one number and one special character from $_@!%*#?&")
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

    @Value("Native")
    private String type;

    @JsonIgnore
    public CharSequence getPassword() {
        return password;
    }
}
