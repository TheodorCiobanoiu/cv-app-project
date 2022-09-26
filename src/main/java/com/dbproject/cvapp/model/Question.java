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
    private String QuestionBody;
//    private Answer QuestionAnswer;
}
