package com.joselio.Conversor.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(org.springframework.http.HttpStatus.BAD_REQUEST)
public class NegativeAmountNotAllowedException extends RuntimeException {
    public NegativeAmountNotAllowedException(String message) {
        super(message);
    }
}
