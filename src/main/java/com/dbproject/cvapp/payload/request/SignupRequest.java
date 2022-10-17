package com.dbproject.cvapp.payload.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SignupRequest {
    @NotBlank
    @Size(max = 120)
    private String firstName;
    @NotBlank
    @Size(max = 120)
    private String lastName;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 5, max = 20)
    private String phone;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private String role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;


}