package com.codingshadows.auth.payload.response;

import com.codingshadows.auth.model.RefreshToken;

public record AuthenticationResponse(String jwt, RefreshToken refreshToken) {
}
