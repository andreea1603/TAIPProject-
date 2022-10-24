package com.example.neurodiagnosis.service.email;

import java.util.Map;

public class EmailService implements IEmailService{
    @Override
    public void sendTemplatedEmail(String templatePath, Map<String, String> substitutions, String destinationEmail, String sourceEmail) {
    }
}
