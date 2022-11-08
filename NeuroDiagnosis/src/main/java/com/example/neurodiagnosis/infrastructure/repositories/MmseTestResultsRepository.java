package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IMmseTestResultsRepository;
import com.example.neurodiagnosis.application.service.database.IDatabaseContext;
import com.example.neurodiagnosis.domain.entities.TestResult;
import com.example.neurodiagnosis.infrastructure.repositories.base.BaseRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Named("mmseTestResultsRepository")
public class MmseTestResultsRepository extends BaseRepository
        implements IMmseTestResultsRepository, Serializable {
    @Inject
    public MmseTestResultsRepository(@Named("DatabaseContextLive") IDatabaseContext databaseContext) {
        super(databaseContext);
    }

    @Override
    public TestResult addNewTestResultsEntry(UUID userId, Date dateTimeOffset, int score) {
        try {
            TestResult testResult = new TestResult();
            testResult.setUserId(userId);
            testResult.setTestResult(score);
            testResult.setTestDate(dateTimeOffset);

            if(!em.getTransaction().isActive()){
                em.getTransaction().begin();
            }

            em.persist(testResult);
            em.getTransaction().commit();

            return testResult;
        }
        catch (Exception e){
            return null;
        }
    }
}
