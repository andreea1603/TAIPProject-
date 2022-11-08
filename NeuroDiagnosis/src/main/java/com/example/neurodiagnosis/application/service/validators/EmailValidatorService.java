package com.example.neurodiagnosis.application.service.validators;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("emailValidator")
@SessionScoped
public class EmailValidatorService implements IEmailValidatorService, Serializable {
    @Override
    public boolean validateEmail(String email) {

        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        return email.matches(regex);
    }
}
