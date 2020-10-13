package com.cognizant.authapi.users.repos;

import com.cognizant.authapi.users.beans.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 784420 on 7/17/2019 7:24 PM
 */
@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    List<Account> findByUserId(String userId);
    void deleteByIdIn(List<String> accountIds);
    void deleteByUserId(String userId);
}
