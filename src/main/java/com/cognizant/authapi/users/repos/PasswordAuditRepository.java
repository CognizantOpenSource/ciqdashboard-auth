package com.cognizant.authapi.users.repos;

import com.cognizant.authapi.users.beans.PasswordAudit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by 784420 on 7/17/2019 7:24 PM
 */
@Repository
public interface PasswordAuditRepository extends MongoRepository<PasswordAudit, String> {
}
