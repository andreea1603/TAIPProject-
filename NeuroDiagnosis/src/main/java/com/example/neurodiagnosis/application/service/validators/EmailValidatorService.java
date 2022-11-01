package com.example.neurodiagnosis.application.service.validators;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("emailValidator")
@SessionScoped
public class EmailValidatorService implements IEmailValidatorService, Serializable {
    @Override
    public boolean validateEmail(String email) {
        return true;
    }
}
