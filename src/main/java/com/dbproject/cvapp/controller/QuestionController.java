package com.dbproject.cvapp.controller;

import com.dbproject.cvapp.model.Question;
import com.dbproject.cvapp.service.QuestionService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("question")
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("all")
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    // TODO: test this function
    @PostMapping("add")
    public void createQuestion(@RequestBody Question question) {
        questionService.createQuestion(question);
    }
}
