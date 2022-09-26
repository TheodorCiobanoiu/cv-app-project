package com.dbproject.cvapp.service;

import com.dbproject.cvapp.exception.RecommendationNotFoundException;
import com.dbproject.cvapp.model.Recommendation;
import com.dbproject.cvapp.model.Status;
import com.dbproject.cvapp.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;

    public void changeRecommendationStatus(Status progressStatus, Integer recommendationId)
            throws RecommendationNotFoundException {
        Optional<Recommendation> tmpOptionalRecommendation = recommendationRepository.findById(recommendationId);
        if(tmpOptionalRecommendation.isEmpty()) {
            throw new RecommendationNotFoundException();
        } else {
            tmpOptionalRecommendation.get().setProgressStatus(progressStatus);
        }
    }
}
