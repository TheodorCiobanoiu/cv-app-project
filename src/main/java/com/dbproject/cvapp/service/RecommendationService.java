package com.dbproject.cvapp.service;

import com.dbproject.cvapp.exception.RecommendationNotFoundException;
import com.dbproject.cvapp.model.Recommendation;
import com.dbproject.cvapp.model.Status;
import com.dbproject.cvapp.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;

    // Method to change the status of a recommendation
    public void changeRecommendationStatus(Status progressStatus, Integer recommendationId)
            throws RecommendationNotFoundException {
        Optional<Recommendation> tmpOptionalRecommendation = recommendationRepository.findById(recommendationId);
        if(tmpOptionalRecommendation.isEmpty()) {
            throw new RecommendationNotFoundException();
        } else {
            Recommendation tmpRecommendation = tmpOptionalRecommendation.get();
            tmpRecommendation.setProgressStatus(progressStatus);
            recommendationRepository.save(tmpRecommendation);
        }
    }

    // Method to add a new recommendation
    //@theo: Changed method to return Recommendation body for testing purposes
    //TODO: After testing is done, change service to void
    //TODO: Maybe check for recommendation body to see if everything is ok
    public Recommendation addRecommendation(Recommendation recommendation) {
        return recommendationRepository.save(recommendation);
    }

    // Method to get all recommendations
    public List<Recommendation> getAllRecommendations() {
        return new ArrayList<>(recommendationRepository.findAll());
    }

    // Method to get all recommendations of a given user
    public List<Recommendation> getRecommendationsById(Integer userId) {
        return recommendationRepository.findAll().stream().filter(x -> Objects.equals(x.getUserId(), userId)).collect(Collectors.toList());
    }

}
