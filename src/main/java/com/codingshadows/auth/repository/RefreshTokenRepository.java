package com.codingshadows.auth.repository;

import com.codingshadows.auth.model.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    void deleteByUserId(String userId);
}
