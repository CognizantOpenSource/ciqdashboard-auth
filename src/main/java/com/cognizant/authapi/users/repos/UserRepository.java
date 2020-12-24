package com.cognizant.authapi.users.repos;

import com.cognizant.authapi.users.beans.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by 784420 on 7/17/2019 7:24 PM
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmailIgnoreCase(String email);

    Optional<User> findByEmailIgnoreCaseAndType(String email, String type);

    void deleteByIdIn(List<String> ids);

    List<User> findAllByEmailIn(List<String> emails);
}
