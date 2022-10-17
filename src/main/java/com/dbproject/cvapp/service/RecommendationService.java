package com.dbproject.cvapp.service;

import com.dbproject.cvapp.dto.RecommendationDTO;
import com.dbproject.cvapp.exception.RecommendationNotFoundException;
import com.dbproject.cvapp.mapper.AnswerMapper;
import com.dbproject.cvapp.mapper.RecommendationMapper;
import com.dbproject.cvapp.model.Answer;
import com.dbproject.cvapp.model.Recommendation;
import com.dbproject.cvapp.model.Status;
import com.dbproject.cvapp.repository.AnswerRepository;
import com.dbproject.cvapp.repository.QuestionRepository;
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
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final RecommendationMapper recommendationMapper;
    private final QuestionRepository questionRepository;

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
        // TODO: CHANGE WITH DTO
        recommendationRepository.save(recommendation);
        for (Answer answer: recommendation.getAnswers()) {
            answer.setQuestion(questionRepository.findById(1).get());
            answer.setRecommendation(recommendation);
            answerRepository.save(answer);
        }
        return recommendation;
    }

    // Method to get all recommendations
    public List<RecommendationDTO> getAllRecommendations() {
//        return new ArrayList<>(recommendationRepository.findAll());
        return recommendationRepository.findAll().stream().map(recommendationMapper::toRecommendationDTO)
//                .map(recommendation -> {
//            recommendation.setAnswerDTOS(recommendation.getAnswerDTOS().stream().map(answerMapper::toAnswerDTO).collect(Collectors.toList()));
//            recommendation.getAnswers().stream().map(answerMapper::toAnswerDTO);
//        })
            .collect(Collectors.toList());
    }

//    recommendation.getAnswers().stream().map(answerMapper::toAnswerDTO);

    // Method to get all recommendations of a given user
    public List<Recommendation> getRecommendationsById(Integer userId) {
        return recommendationRepository.findAll().stream().filter(x -> Objects.equals(x.getUserId(), userId)).collect(Collectors.toList());
    }

}
