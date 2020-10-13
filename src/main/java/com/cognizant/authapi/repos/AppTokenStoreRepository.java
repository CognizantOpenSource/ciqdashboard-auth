package com.cognizant.authapi.repos;

import com.cognizant.authapi.models.AppTokenStore;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AppTokenStoreRepository extends MongoRepository<AppTokenStore, String> {
    Optional<AppTokenStore> findByRefId(String refId);

    void deleteByRefId(String id);
}
