package com.dbproject.cvapp.repository;

import com.dbproject.cvapp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
