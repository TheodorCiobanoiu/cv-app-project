package com.dbproject.cvapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //TODO: See how to make connection between Question and Answer using only Id
    //private Integer questionId;
    //TODO: See how to make connection between Recommendation and Answer using only Id
    //private Integer recommendationId;
    private String answerBody;
    @ManyToOne(fetch = FetchType.LAZY)
    private Recommendation recommendation;
    @ManyToOne
    private Question question; // question Id maybe
//    private Integer questionId;

}
