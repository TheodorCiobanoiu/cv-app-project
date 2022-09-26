package com.dbproject.cvapp.controller;

import com.dbproject.cvapp.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecommendationController {
    private final RecommendationService recommendationService;


}
