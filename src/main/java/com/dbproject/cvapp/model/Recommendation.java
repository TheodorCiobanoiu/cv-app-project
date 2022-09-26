package com.dbproject.cvapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer userId;

    @NotNull
    private String candidateFirstName;

    @NotNull
    private String candidateLastName;

    @NotNull
    private String candidateEmail;

    @NotNull
    private String candidatePhoneNumber;

    @NotNull
    private Status progressStatus;

    // TODO: Store CV
    // TODO: Answers[]
}
