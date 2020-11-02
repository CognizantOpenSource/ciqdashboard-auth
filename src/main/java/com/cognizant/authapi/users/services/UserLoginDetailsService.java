package com.cognizant.authapi.users.services;

import com.cognizant.authapi.users.beans.UserLoginDetails;
import com.cognizant.authapi.users.repos.UserLoginDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginDetailsService {
    @Autowired
    UserLoginDetailsRepository repository;

    public Optional<UserLoginDetails> get(String userEmailId){
        return repository.findById(userEmailId);
    }

    public UserLoginDetails save(UserLoginDetails userLoginDetails){
        return repository.save(userLoginDetails);
    }
}
