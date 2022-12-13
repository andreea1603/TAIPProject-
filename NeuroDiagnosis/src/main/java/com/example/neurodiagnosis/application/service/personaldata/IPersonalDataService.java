package com.example.neurodiagnosis.application.service.personaldata;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.UUID;

public interface IPersonalDataService {
    String getMlResult(UUID userId) throws IOException, InterruptedException;
}
