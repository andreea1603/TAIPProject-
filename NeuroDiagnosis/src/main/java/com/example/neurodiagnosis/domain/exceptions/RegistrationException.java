package com.example.neurodiagnosis.domain.exceptions;

public class RegistrationException extends Exception{
    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(Throwable cause) {
        super(cause);
    }
}
