package com.dbproject.cvapp.exception;

public class RecommendationNotFoundException extends Exception {
    public RecommendationNotFoundException() {
        super("Recommendation not found");
    }
}
