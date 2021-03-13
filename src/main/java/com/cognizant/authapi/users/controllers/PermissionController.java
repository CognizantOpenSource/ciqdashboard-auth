package com.cognizant.authapi.users.controllers;

import com.cognizant.authapi.base.error.PermissionNotFoundException;
import com.cognizant.authapi.users.beans.Permission;
import com.cognizant.authapi.users.services.PermissionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Created by 784420 on 7/23/2019 3:40 PM
 */
@RestController
@RequestMapping(value = "/permissions")
@AllArgsConstructor
@Slf4j
public class PermissionController {

    private PermissionService permissionService;

    /**
     * Getting all Permissions
     *
     * @return list of permissions which are available in db
     */
    @GetMapping(value = "")
    @PreAuthorize("hasPermission('Permissions','ciqdashboard.permission.read')")
    public List<Permission> getAllPermissions() {
        log.info("Getting all the Permissions from Database.....!");
        return permissionService.getAllPermissions();
    }


    /**
     * Getting a Permission based on provided ID
     * @param permissionId id of permission which need to fetch from db
     * @return permission details based on the id
     */
    @GetMapping(value = "/{permissionId}")
    @PreAuthorize("hasPermission('Permissions','ciqdashboard.permission.read')")
    public Permission getPermission(@PathVariable String permissionId) {
        log.info("Getting Project id is : " + permissionId);
        Optional<Permission> optionalPermission = permissionService.getPermission(permissionId);
        if (optionalPermission.isPresent()) {
            return optionalPermission.get();
        } else {
            throw new PermissionNotFoundException(permissionId);
        }
    }
}
