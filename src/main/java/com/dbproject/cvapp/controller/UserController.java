package com.dbproject.cvapp.controller;

import com.dbproject.cvapp.exception.JwtException;
import com.dbproject.cvapp.exception.TokenRefreshException;
import com.dbproject.cvapp.model.RefreshToken;
import com.dbproject.cvapp.model.User;
import com.dbproject.cvapp.payload.request.AuthenticationRequest;
import com.dbproject.cvapp.payload.request.LogOutRequest;
import com.dbproject.cvapp.payload.request.TokenRefreshRequest;
import com.dbproject.cvapp.payload.response.AuthenticationResponse;
import com.dbproject.cvapp.payload.response.MessageResponse;
import com.dbproject.cvapp.payload.response.TokenRefreshResponse;
import com.dbproject.cvapp.repository.UserRepository;
import com.dbproject.cvapp.service.RefreshTokenService;
import com.dbproject.cvapp.service.UserDetailsServiceImpl;
import com.dbproject.cvapp.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtTokenUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final RefreshTokenService refreshTokenService;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping(value = "/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt, refreshTokenService.createRefreshToken(authenticationRequest.getEmail())));
    }

    @PostMapping(value = "/register")
    public String registerUser(@RequestBody AuthenticationRequest authenticationRequest) {
        if (userRepository.findUserByEmail(authenticationRequest.getEmail()) != null)
            return "The user already exists.";

        User user = new User();
        user.setUserID(UUID.randomUUID().toString());
        user.setEmail(authenticationRequest.getEmail());
        user.setUsername(authenticationRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(authenticationRequest.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        userRepository.save(user);
        return "The user was created.";
    }

    @GetMapping(value = "/bps/data/user/{JWT}")
    public User getUserFromJWTPV(@PathVariable String JWT) throws JwtException {
        return getUserFromJWT(JWT);
    }

    @GetMapping(value = "/data/user")
    public User getUserFromJWTQP(@RequestParam String JWT) throws JwtException {
        return getUserFromJWT(JWT);
    }

    public User getUserFromJWT(String JWT) throws JwtException {
        String email = jwtUtil.extractEmail(JWT);

        //noinspection DuplicatedCode
        if (email != null) {
            UserDetails userDetails = this.userDetailsService.loadUserByEmail(email);
            if (jwtUtil.validateToken(JWT, userDetails)) {
                return userRepository.findUserByEmail(email);
            }
        }
        return null;
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserId)
                .map(user -> {
                    String token = jwtUtil.generateToken(userDetailsService.loadUserByUsername(user));
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
        refreshTokenService.deleteByUserId(logOutRequest.getUserId());
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }
}
