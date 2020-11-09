package com.cognizant.authapi.users.repos;

import com.cognizant.authapi.users.beans.UserLoginDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserLoginDetailsRepository extends MongoRepository<UserLoginDetails, String> {
}
