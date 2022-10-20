package com.dbproject.cvapp.mapper;

import com.dbproject.cvapp.dto.RecommendationDTO;
import com.dbproject.cvapp.model.Recommendation;
import com.dbproject.cvapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationMapperImpl implements RecommendationMapper{
    private final AnswerMapper answerMapper;
    private final UserRepository userRepository;

    @Override
    public RecommendationDTO toRecommendationDTO(Recommendation recommendation) {
        if (recommendation == null) {
            return null;
        }
        return new RecommendationDTO(recommendation.getId(),
                userRepository.findById(recommendation.getUserId()).get().getFirstName() + " " +
                        userRepository.findById(recommendation.getUserId()).get().getLastName(),
                recommendation.getCandidateFirstName(),
                recommendation.getCandidateLastName(),
                recommendation.getCandidateEmail(),
                recommendation.getCandidatePhoneNumber(),
                recommendation.getProgressStatus(),
                recommendation.getCvFileId(),
                recommendation.getAnswers().stream().map(answerMapper::toAnswerDTO).collect(Collectors.toList()));
    }
}
