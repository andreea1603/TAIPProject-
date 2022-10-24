package com.example.neurodiagnosis.service.email;

import java.util.Map;

public interface IEmailService {
    void sendTemplatedEmail(String templatePath, Map<String, String> substitutions, String destinationEmail, String sourceEmail);
}
