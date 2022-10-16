package com.dbproject.cvapp.mapper;

import com.dbproject.cvapp.dto.AnswerDTO;
import com.dbproject.cvapp.model.Answer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class AnswerMapper {
    AnswerDTO toAnswerDTO(Answer answer);
}
