package com.example.neurodiagnosis.application.service.validators;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("emailValidator")
@SessionScoped
public class EmailValidatorService implements IEmailValidatorService, Serializable {
    @Override
    public boolean validateEmail(String email) {

        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        return email.matches(regex);
    }
}
