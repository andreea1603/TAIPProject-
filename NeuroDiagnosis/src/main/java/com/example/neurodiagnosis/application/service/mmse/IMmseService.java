package com.example.neurodiagnosis.application.service.mmse;


import com.example.neurodiagnosis.domain.entities.TestResult;

import java.util.List;
import java.util.UUID;
import java.util.Date;

public interface IMmseService {
    TestResult submitTestResults(UUID userId, Date dateTimeOffset, int score);
    List<TestResult> getHistory(UUID userId);
}
