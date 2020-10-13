package com.cognizant.authapi.users.repos;

import com.cognizant.authapi.users.beans.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 784420 on 7/17/2019 7:24 PM
 */
@Repository
public interface PermissionRepository extends MongoRepository<Permission, String> {
    List<Permission> findByIdLike(String id);
}
