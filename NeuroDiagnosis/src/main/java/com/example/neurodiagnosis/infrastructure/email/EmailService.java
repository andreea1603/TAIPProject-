package com.example.neurodiagnosis.infrastructure.email;

import com.example.neurodiagnosis.application.interfaces.email.IEmailService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Map;

@Named("emailService")
@SessionScoped
public class EmailService implements IEmailService, Serializable {
    @Override
    public void sendTemplatedEmail(String templatePath, Map<String, String> substitutions, String destinationEmail, String sourceEmail) {
        // Not needed
    }
}
