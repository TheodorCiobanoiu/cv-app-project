package com.dbproject.cvapp.dto;

import com.dbproject.cvapp.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationDTO {
    private Integer id;
    private String userFullName;
    private String candidateFirstName;
    private String candidateLastName;
    private String candidateEmail;
    private String candidatePhoneNumber;
    private Status progressStatus;
    private Integer cvFileId;
    private List<AnswerDTO> answerDTOS;
}
