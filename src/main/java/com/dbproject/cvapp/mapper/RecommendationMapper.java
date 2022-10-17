package com.dbproject.cvapp.mapper;

import com.dbproject.cvapp.dto.RecommendationDTO;
import com.dbproject.cvapp.model.Recommendation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

public interface RecommendationMapper {
    RecommendationDTO toRecommendationDTO(Recommendation recommendation);
}
