package com.dbproject.cvapp.mapper;

import com.dbproject.cvapp.dto.RecommendationDTO;
import com.dbproject.cvapp.model.Recommendation;

public interface RecommendationMapper {
    RecommendationDTO toRecommendationDTO(Recommendation recommendation);
}
