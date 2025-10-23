package com.joselio.Conversor.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR)
public class ConversionRateNotFoundException extends RuntimeException {
    public ConversionRateNotFoundException(String message) {
        super(message);
    }
}
