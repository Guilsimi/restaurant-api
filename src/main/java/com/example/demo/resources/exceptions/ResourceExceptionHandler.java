package com.example.demo.resources.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.services.exception.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest req) {
        
        HttpStatus stats = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), stats.value(), "Não encontrado", e.getMessage(), req.getRequestURI());

        return ResponseEntity.status(stats).body(err);
    }

}
