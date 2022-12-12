package com.example.neurodiagnosis.application.service.personaldata;

import com.example.neurodiagnosis.webapi.dtos.PersonalDataMLDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.util.UUID;

public interface IPersonalDataService {
    String getMlResult(UUID userId, PersonalDataMLDTO personalDataMLDTO) throws JsonProcessingException, IOException, InterruptedException;
}
