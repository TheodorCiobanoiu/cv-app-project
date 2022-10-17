package com.dbproject.cvapp.dto;

import com.dbproject.cvapp.model.Question;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerDTO {
    private String answerBody;
    private Question question;
}
