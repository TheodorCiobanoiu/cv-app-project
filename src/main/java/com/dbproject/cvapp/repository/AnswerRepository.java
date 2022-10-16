package com.dbproject.cvapp.repository;

import com.dbproject.cvapp.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
