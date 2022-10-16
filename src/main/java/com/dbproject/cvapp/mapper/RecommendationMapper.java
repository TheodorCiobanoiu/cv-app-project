package com.dbproject.cvapp.mapper;

import com.dbproject.cvapp.dto.RecommendationDTO;
import com.dbproject.cvapp.model.Recommendation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecommendationMapper {
    RecommendationDTO toRecommendationDTO(Recommendation recommendation);
}
