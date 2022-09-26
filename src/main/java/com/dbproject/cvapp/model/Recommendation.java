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

    private Integer userId;
    private String candidateFirstName;
    private String candidateLastName;
    private String candidateEmail;
    private String candidatePhoneNumber;
    private Status progressStatus;

    // TODO: Store CV
    // TODO: Answers[]
}
