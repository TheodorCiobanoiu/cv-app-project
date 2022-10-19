package com.dbproject.cvapp.service;

import com.dbproject.cvapp.model.Question;
import com.dbproject.cvapp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    // Method to add a question to the db
    public void createQuestion(Question question) {
        questionRepository.save(question);
    }

    // Method to get all questions
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questionRepository.findAll());
    }

    public void deleteQuestion(Integer id){
        questionRepository.deleteById(id);
    }
}
