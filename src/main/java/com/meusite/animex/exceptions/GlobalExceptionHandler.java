package com.meusite.animex.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Manipulador global de exceções personalizadas

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handle(CustomException exception) {

        ApplicationErrors applicationError = new ApplicationErrors(
                exception.getMessage(),
                exception.getStatus().value(),
                LocalDateTime.now());
        return new ResponseEntity<>(applicationError, exception.getStatus());
    }
}
