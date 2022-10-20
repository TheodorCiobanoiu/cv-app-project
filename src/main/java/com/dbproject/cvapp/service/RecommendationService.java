package com.dbproject.cvapp.service;

import com.dbproject.cvapp.dto.RecommendationDTO;
import com.dbproject.cvapp.exception.RecommendationNotFoundException;
import com.dbproject.cvapp.mapper.RecommendationMapper;
import com.dbproject.cvapp.model.Answer;
import com.dbproject.cvapp.model.Recommendation;
import com.dbproject.cvapp.model.Status;
import com.dbproject.cvapp.repository.AnswerRepository;
import com.dbproject.cvapp.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepository recommendationRepository;
    private final AnswerRepository answerRepository;
    private final RecommendationMapper recommendationMapper;

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
    public RecommendationDTO addRecommendation(Recommendation recommendation) {
        recommendationRepository.save(recommendation);
        for (Answer answer: recommendation.getAnswers()) {
            answer.setRecommendation(recommendation);
            answerRepository.save(answer);
        }
        return recommendationMapper.toRecommendationDTO(recommendation);
    }

    // Method to get all recommendations
    public List<RecommendationDTO> getAllRecommendations() {
        return recommendationRepository.findAll().stream().map(recommendationMapper::toRecommendationDTO)
            .collect(Collectors.toList());
    }

    // Method to get all recommendations of a given user
    public List<RecommendationDTO> getRecommendationsById(Integer userId) {
        return recommendationRepository.findAll().stream()
                .filter(x -> Objects.equals(x.getUserId(), userId))
                .map(recommendationMapper::toRecommendationDTO)
                .collect(Collectors.toList());
    }

    public void deleteRecommendation(Integer id){
        recommendationRepository.deleteById(id);
    }

    public Recommendation getRecommendationById(Integer id) throws RecommendationNotFoundException {
        Optional<Recommendation> recommendationTmp = recommendationRepository.findById(id);
        if (recommendationTmp.isEmpty()) {
            throw new RecommendationNotFoundException();
        }
        return recommendationTmp.get();
    }

}
