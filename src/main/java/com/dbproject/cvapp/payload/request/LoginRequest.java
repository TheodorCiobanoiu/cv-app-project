package com.dbproject.cvapp.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
//    @NotBlank
//    private String email;
    @NotBlank
    private String username;

    @NotBlank
    private String password;


}