package com.dbproject.cvapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


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
    private Integer cvFileId;
    //theo: nu sunt sigur daca e corecta legatura asta in db pentru raspunsurile la intrebari
    @OneToMany(mappedBy = "recommendation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;
    // TODO: Store CV
    // TODO: Answers[]
}
