package com.dbproject.cvapp.repository;

import com.dbproject.cvapp.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationRepository extends JpaRepository<Recommendation, Integer> {
}
