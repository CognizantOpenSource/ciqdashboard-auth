package com.cognizant.authapi.users.repos;

import com.cognizant.authapi.base.beans.WhiteList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WhiteListRepository extends MongoRepository<WhiteList, String> {
}
