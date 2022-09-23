package com.dbproject.cvapp.repository;

import com.dbproject.cvapp.model.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    void deleteByUserId(String userId);
}
