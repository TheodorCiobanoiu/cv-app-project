package com.codingshadows.auth.repository;

import com.codingshadows.auth.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);

    User findUserByEmail(String email);

    User findUserByUserID(String userID);
}