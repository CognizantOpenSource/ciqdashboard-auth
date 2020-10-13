package com.cognizant.authapi.users.dto;

import com.cognizant.authapi.users.beans.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by 784420 on 7/19/2019 6:33 PM
 */
@Data
public class AccountDTO {
    private String id;
    @NotBlank(message = "UserId should not be empty")
    @Size(min = 2, message = "UserId should have at least 2 characters")
    private String userId;
    private List<Role> roles;
    @NotEmpty(message = "RoleId's List should not be empty")
    @Size(min = 1, message = "RoleId's List should have at least 1 RoleId")
    private List<String> roleIds;
    private List<String> projectIds;
}
