package com.example.neurodiagnosis.infrastructure.email;

import com.example.neurodiagnosis.application.interfaces.email.IEmailService;

import java.util.Map;

public class EmailService implements IEmailService {
    @Override
    public void sendTemplatedEmail(String templatePath, Map<String, String> substitutions, String destinationEmail, String sourceEmail) {
    }
}
