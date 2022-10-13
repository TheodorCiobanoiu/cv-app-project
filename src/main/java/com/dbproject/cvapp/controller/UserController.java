package com.dbproject.cvapp.controller;

import com.dbproject.cvapp.model.MyUser;
import com.dbproject.cvapp.model.Roles;
import com.dbproject.cvapp.model.UserRoles;
import com.dbproject.cvapp.payload.request.LoginRequest;
import com.dbproject.cvapp.payload.request.SignupRequest;
import com.dbproject.cvapp.payload.response.JwtResponse;
import com.dbproject.cvapp.payload.response.MessageResponse;
import com.dbproject.cvapp.repository.RolesRepository;
import com.dbproject.cvapp.repository.UserRepository;
import com.dbproject.cvapp.security.jwt.JwtUtils;
import com.dbproject.cvapp.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final JwtUtils jwtUtil;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
//    private final UserDetailsServiceImpl userDetailsService;
//    private final RefreshTokenService refreshTokenService;

    private final RolesRepository roleRepository;
//    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final PasswordEncoder encoder;

    @PostMapping(value = "/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        final String jwt = jwtUtil.generateJwtToken(authentication);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

//    @PostMapping(value = "/register")
//    public String registerUser(@RequestBody AuthenticationRequest authenticationRequest) {
//        if (userRepository.findUserByEmail(authenticationRequest.getEmail()).isPresent())
//            return "The user already exists.";
//
//        MyUser user = new MyUser();
//        user.setEmail(authenticationRequest.getEmail());
//        user.setUsername(authenticationRequest.getEmail());
//        user.setPassword(bCryptPasswordEncoder.encode(authenticationRequest.getPassword()));
//        user.setEnabled(true);
//        user.setAccountNonExpired(true);
//        user.setAccountNonLocked(true);
//        user.setCredentialsNonExpired(true);
//        userRepository.save(user);
//        return "The user was created.";
//    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        MyUser user = new MyUser(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Roles> roles = new HashSet<>();

        if (strRoles == null) {
            Roles userRole = roleRepository.findByName(UserRoles.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Roles adminRole = roleRepository.findByName(UserRoles.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "hr" -> {
                        Roles hrRole = roleRepository.findByName(UserRoles.ROLE_HR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(hrRole);
                    }
                    default -> {
                        Roles employeeRole = roleRepository.findByName(UserRoles.ROLE_EMPLOYEE)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(employeeRole);
                    }
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

//    @GetMapping(value = "/bps/data/user/{JWT}")
//    public MyUser getUserFromJWTPV(@PathVariable String JWT) throws JwtException {
//        return getUserFromJWT(JWT);
//    }
//
//    @GetMapping(value = "/data/user")
//    public MyUser getUserFromJWTQP(@RequestParam String JWT) throws JwtException {
//        return getUserFromJWT(JWT);
//    }
//
//    public MyUser getUserFromJWT(String JWT) throws JwtException {
//        String email = jwtUtil.extractEmail(JWT);
//
//        //noinspection DuplicatedCode
//        if (email != null) {
//            UserDetails userDetails = this.userDetailsService.loadUserByEmail(email);
//            if (jwtUtil.validateToken(JWT, userDetails)) {
//                return userRepository.findUserByEmail(email)
//                        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));
//            }
//        }
//        return null;
//    }

//    @PostMapping("/refresh-token")
//    public ResponseEntity<?> refreshToken(@RequestBody TokenRefreshRequest request) {
//        String requestRefreshToken = request.getRefreshToken();
//
//        return refreshTokenService.findByToken(requestRefreshToken)
//                .map(refreshTokenService::verifyExpiration)
//                .map(RefreshToken::getUserId)
//                .map(user -> {
//                    String token = jwtUtil.generateToken(userDetailsService.loadUserByUsername(user));
//                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
//                })
//                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
//                        "Refresh token is not in database!"));
//    }

//    @PostMapping("/logout")
//    public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {
//        refreshTokenService.deleteByUserId(logOutRequest.getUserId());
//        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
//    }
}
