package com.codingshadows.auth.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document
public class RefreshToken {
    @Id
    private String token;
    private String userId;
    private Instant expiryDate;
}
