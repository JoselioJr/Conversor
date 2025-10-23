package com.joselio.Conversor.handler;

import java.io.UnsupportedEncodingException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.joselio.Conversor.exception.ConversionRateNotFoundException;
import com.joselio.Conversor.exception.NegativeAmountNotAllowedException;
import com.joselio.Conversor.exception.UnsupportedUnitException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UnsupportedEncodingException.class)
    public ResponseEntity<String> handleUnsupportedUnitException(UnsupportedUnitException e) {
        return new ResponseEntity<>(e.getMessage(), org.springframework.http.HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NegativeAmountNotAllowedException.class)
    public ResponseEntity<String> handleNegativeAmountNotAllowedException(NegativeAmountNotAllowedException e) {
        return new ResponseEntity<>(e.getMessage(), org.springframework.http.HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConversionRateNotFoundException.class)
    public ResponseEntity<String> handleConversionRateNotFoundException(ConversionRateNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        return new ResponseEntity<>("Erro de Validação: " + errors, HttpStatus.BAD_REQUEST);
    }
}
