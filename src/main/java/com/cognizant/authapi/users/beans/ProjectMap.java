package com.cognizant.authapi.users.beans;

import lombok.Data;

import java.util.List;

/**
 * Created by 784420 on 7/19/2019 4:04 PM
 */
@Data
public class ProjectMap {
    private String projectId;
    private List<String> users;
}
