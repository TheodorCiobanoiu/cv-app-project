package com.dbproject.cvapp.mapper;

import com.dbproject.cvapp.dto.AnswerDTO;
import com.dbproject.cvapp.model.Answer;
import org.springframework.stereotype.Service;

@Service
public class AnswerMapperImpl implements AnswerMapper {

    @Override
    public AnswerDTO toAnswerDTO(Answer answer) {
        if (answer == null) {
            return null;
        }
        return new AnswerDTO(answer.getAnswerBody(),answer.getQuestion());
    }
}
