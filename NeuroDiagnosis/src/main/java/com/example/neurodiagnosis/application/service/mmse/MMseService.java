package com.example.neurodiagnosis.application.service.mmse;

import com.example.neurodiagnosis.infrastructure.repositories.MmseTestResultsRepository;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
public class MMseService implements IMmseService {
    private MmseTestResultsRepository mmseTestResultsRepository;

    @Override
    public void submitTestResults(UUID userId, Date dateTimeOffset, int score) {
    }
}
