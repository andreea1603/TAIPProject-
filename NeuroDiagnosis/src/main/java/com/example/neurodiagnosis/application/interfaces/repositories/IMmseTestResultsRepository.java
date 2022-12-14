package com.example.neurodiagnosis.application.interfaces.repositories;

import com.example.neurodiagnosis.domain.entities.TestResult;
import com.example.neurodiagnosis.infrastructure.repositories.base.IRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IMmseTestResultsRepository extends IRepository {
    TestResult addNewTestResultsEntry(UUID userId, Date dateTimeOffset, int score);

    List<TestResult> getTestResults(UUID userId);
}
