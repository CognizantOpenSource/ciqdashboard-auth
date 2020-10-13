package com.cognizant.authapi.users.dto;

import com.cognizant.authapi.users.beans.Permission;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by 784420 on 7/19/2019 4:01 PM
 */
@Data
public class RoleDTO {
    @NotBlank(message = "Name should not be empty")
    @Size(min = 4, message = "Name should have at least 4 characters")
    private String name;
    private String desc;
    private List<Permission> permissions;
    @NotEmpty(message = "PermissionId's List should not be empty")
    @Size(min = 1, message = "PermissionId's List should have at least 1 PermissionId")
    private List<String> permissionIds;
}
