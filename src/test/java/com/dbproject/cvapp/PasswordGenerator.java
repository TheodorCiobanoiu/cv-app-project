package com.dbproject.cvapp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "ioana";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);
    }
}
