package com.dbproject.cvapp.controller;

import com.dbproject.cvapp.model.Question;
import com.dbproject.cvapp.service.QuestionService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("add")
    public void createQuestion(@RequestBody Question question) {
        questionService.createQuestion(question);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public void deleteQuestion(@PathVariable Integer id){
        questionService.deleteQuestion(id);
    }
}
