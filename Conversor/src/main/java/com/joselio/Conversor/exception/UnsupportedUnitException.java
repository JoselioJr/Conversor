package com.joselio.Conversor.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
public class UnsupportedUnitException extends RuntimeException {
    public UnsupportedUnitException(String message) {
        super(message);
    }
}
