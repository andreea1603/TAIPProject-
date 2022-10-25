package com.example.neurodiagnosis.application.interfaces.email;

import java.util.Map;

public interface IEmailService {
    void sendTemplatedEmail(String templatePath, Map<String, String> substitutions, String destinationEmail, String sourceEmail);
}
