package com.dbproject.cvapp.payload.response;

import com.dbproject.cvapp.model.RefreshToken;

public record AuthenticationResponse(String jwt, RefreshToken refreshToken) {
}
