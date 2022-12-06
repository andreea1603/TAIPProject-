package com.example.neurodiagnosis.application.service.personaldata;

import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.webapi.dtos.PersonalDataDTO;

import java.util.UUID;

public interface IPersonalDataService {
    User updateData(UUID userId, PersonalDataDTO personalDataDTO);
}
