package com.example.neurodiagnosis.domain.exceptions;

public class JwtValidationException extends Exception{
    public JwtValidationException(String message) {
        super(message);
    }

    public JwtValidationException(Throwable cause) {
        super(cause);
    }
}
