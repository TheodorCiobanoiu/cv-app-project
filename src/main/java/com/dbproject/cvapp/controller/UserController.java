package com.dbproject.cvapp.controller;


import com.dbproject.cvapp.model.User;
import com.dbproject.cvapp.repository.UserRepository;
import com.dbproject.cvapp.service.UserDetailsServiceImpl;
import com.dbproject.cvapp.util.JwtUtil;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final JwtUtil jwtTokenUtil;
    private final UserDetailsServiceImpl userDetailsService;
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping("register")
    public User registerUser(@RequestBody User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println(user);
        return userRepository.save(user);
    }
}
