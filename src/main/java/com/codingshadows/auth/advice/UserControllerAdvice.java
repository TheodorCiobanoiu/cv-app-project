package com.codingshadows.auth.advice;

import com.codingshadows.auth.exception.JwtException;
import com.codingshadows.auth.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler(value = JwtException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleJWTException(JwtException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleJWTExceptionFromFilter(JwtException ex) {
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                new Date(),
                ex.getMessage(),
                ex.getMessage());
    }
}
