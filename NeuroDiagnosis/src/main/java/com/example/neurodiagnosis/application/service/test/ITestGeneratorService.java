package com.example.neurodiagnosis.application.service.test;


import com.example.neurodiagnosis.domain.entities.TestResult;
import com.example.neurodiagnosis.webapi.dtos.test.TestDTO;

import java.util.Date;
import java.util.UUID;

public interface ITestGeneratorService {
    TestDTO generateTest(UUID userId);
}
