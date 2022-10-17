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
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class UserController {
    private final JwtUtils jwtUtil;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    private final RolesRepository roleRepository;
private final PasswordEncoder encoder;

    @PostMapping(value = "/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        final String jwt = jwtUtil.generateJwtToken(authentication);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        System.out.println("New user sign-in: " + loginRequest);
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

    }
    //@PreAuthorize("hasRole('ADMIN')")
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
        MyUser user = new MyUser(
                signUpRequest.getUsername(),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getPhone(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        String strRoles = signUpRequest.getRole();
        Set<Roles> roles = new HashSet<>();

        if (strRoles == null) {
            Roles userRole = roleRepository.findByName(UserRoles.ROLE_EMPLOYEE)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {

            switch (strRoles) {
                case "ROLE_ADMIN" -> {
                    Roles adminRole = roleRepository.findByName(UserRoles.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                }
                case "ROLE_HR" -> {
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

        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
