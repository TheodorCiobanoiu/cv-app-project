package com.dbproject.cvapp.dto;

import com.dbproject.cvapp.model.Answer;
import com.dbproject.cvapp.model.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RecommendationDTO {
    private Integer userId;
    private String candidateFirstName;
    private String candidateLastName;
    private String candidateEmail;
    private String candidatePhoneNumber;
    private Status progressStatus;
//    private List<AnswerDTO> answerDTOS;
}
