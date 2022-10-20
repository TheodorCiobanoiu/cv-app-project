package com.dbproject.cvapp.controller;

import com.dbproject.cvapp.dto.RecommendationDTO;
import com.dbproject.cvapp.exception.RecommendationNotFoundException;
import com.dbproject.cvapp.model.Answer;
import com.dbproject.cvapp.model.Recommendation;
import com.dbproject.cvapp.model.Status;
import com.dbproject.cvapp.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("recommendation")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RecommendationController {
    private final RecommendationService recommendationService;

    @PreAuthorize("hasRole('HR') or hasRole('ADMIN')")
    @GetMapping("all")
    public List<RecommendationDTO> getAllRecommendations() {
        return recommendationService.getAllRecommendations();
    }

    @GetMapping("all/{id}")
    public List<RecommendationDTO> getRecommendationsById(@PathVariable Integer id) {
        return recommendationService.getRecommendationsById(id);
    }

    @PostMapping("add")
    public RecommendationDTO addRecommendation(@RequestBody Recommendation recommendation) {
        System.out.println("Added new recommendation: " + recommendation);
        return recommendationService.addRecommendation(recommendation);

    }
    @PostMapping("changeStatus/{id}/{status}")
    public void changeRecommendationStatus(@PathVariable Status status, @PathVariable Integer id)
            throws RecommendationNotFoundException {
        recommendationService.changeRecommendationStatus(status, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public void deleteRecommendation(@PathVariable Integer id){
        recommendationService.deleteRecommendation(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('EMPLOYEE') or hasRole('HR')")
    @GetMapping("answers/{id}")
    public List<Answer> getAnswersFromRecommendation(@PathVariable Integer id) throws RecommendationNotFoundException {
        Recommendation recommendation = recommendationService.getRecommendationById(id);
        return recommendation.getAnswers();
    }
}
