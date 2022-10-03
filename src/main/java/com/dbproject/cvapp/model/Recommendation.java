package com.dbproject.cvapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

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
    //theo: nu sunt sigur daca e corecta legatura asta in db pentru raspunsurile la intrebari
    //@OneToMany
    //private ArrayList<Answer> answers;
    // TODO: Store CV
    // TODO: Answers[]
}
