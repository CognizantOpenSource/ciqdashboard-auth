package com.cognizant.authapi.users.util;

import com.cognizant.authapi.users.beans.Permission;
import com.cognizant.authapi.users.beans.Role;
import com.cognizant.authapi.users.dto.RoleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by 784420 on 7/17/2019 7:51 PM
 */
@Component
public class RoleUtil {

    public RoleDTO convertToDto(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        BeanUtils.copyProperties(role, roleDTO);
        roleDTO.setPermissionIds(
                role.getPermissions().stream().map(Permission::getId).collect(toList())
        );
        return roleDTO;
    }

    public List<RoleDTO> convertToDtoList(List<Role> roleList) {
        List<RoleDTO> roleDTOList = new ArrayList<>();
        roleList.forEach(role -> roleDTOList.add(convertToDto(role)));
        return roleDTOList;
    }

    public Role convertToEntity(RoleDTO roleDTO, List<Permission> permissionList) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        role.setPermissions(permissionList);
        return role;
    }

    public Role convertToNewEntity(RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO, role);
        return role;
    }
}
