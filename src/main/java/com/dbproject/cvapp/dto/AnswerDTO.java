package com.dbproject.cvapp.dto;

import com.dbproject.cvapp.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
    private String answerBody;
    private Question question;
}
