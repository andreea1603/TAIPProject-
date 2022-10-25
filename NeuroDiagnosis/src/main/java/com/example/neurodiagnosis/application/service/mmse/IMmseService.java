package com.example.neurodiagnosis.application.service.mmse;


import java.util.UUID;
import java.util.Date;

public interface IMmseService {
    void submitTestResults(UUID userId, Date dateTimeOffset, int score);
}
