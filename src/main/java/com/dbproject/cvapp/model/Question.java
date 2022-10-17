package com.dbproject.cvapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private QuestionType type;
    private String questionBody;
    private String possibleAnswer1;
    private String possibleAnswer2;
    private String possibleAnswer3;
    private String possibleAnswer4;
}
