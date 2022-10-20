package com.dbproject.cvapp.service;

import com.dbproject.cvapp.exception.NoQuestionFoundException;
import com.dbproject.cvapp.model.Question;
import com.dbproject.cvapp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    // Method to add a question to the db
    public void createQuestion(Question question) {
        questionRepository.save(question);
    }

    // Method to get a question by id
    public Question getQuestionById(Integer id) throws NoQuestionFoundException {
        Optional<Question> questionTmp = questionRepository.findById(id);
        if(questionTmp.isEmpty()) {
            throw new NoQuestionFoundException();
        }
        return questionTmp.get();
    }

    // Method to get all questions
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questionRepository.findAll());
    }

    public void deleteQuestion(Integer id){
        questionRepository.deleteById(id);
    }
}
