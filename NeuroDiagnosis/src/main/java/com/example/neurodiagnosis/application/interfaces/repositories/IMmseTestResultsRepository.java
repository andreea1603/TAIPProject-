package com.example.neurodiagnosis.application.interfaces.repositories;
import com.example.neurodiagnosis.domain.entities.TestResult;

import java.util.UUID;
import java.util.Date;

public interface IMmseTestResultsRepository {
    TestResult addNewTestResultsEntry(UUID userId, Date dateTimeOffset, int score);
}
