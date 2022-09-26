package com.dbproject.cvapp.controller;

import com.dbproject.cvapp.model.Question;
import com.dbproject.cvapp.service.QuestionService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("question")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("all")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // TODO: test this function
    @PostMapping("add")
    public void createQuestion(@JsonDeserialize Question question) {
        questionService.createQuestion(question);
    }
}
