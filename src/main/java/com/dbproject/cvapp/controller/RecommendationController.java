package com.dbproject.cvapp.controller;

import com.dbproject.cvapp.exception.RecommendationNotFoundException;
import com.dbproject.cvapp.model.Recommendation;
import com.dbproject.cvapp.model.Status;
import com.dbproject.cvapp.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("recommendation")
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping("all")
    public List<Recommendation> getAllRecommendations() {
        return recommendationService.getAllRecommendations();
    }

    @GetMapping("all/{id}")
    public List<Recommendation> getRecommendationsById(@PathVariable Integer id) {
        return recommendationService.getRecommendationsById(id);
    }

    // TODO: ADD RECOMMENDATION
    // @Vulpe: Nu mai stiu daca stia sa extraga springul singur un obiect

    @PostMapping("changeStatus/{id}/{status}")
    public void changeRecommendationStatus(@PathVariable Status status, @PathVariable Integer id)
            throws RecommendationNotFoundException {
        recommendationService.changeRecommendationStatus(status, id);
    }
}
