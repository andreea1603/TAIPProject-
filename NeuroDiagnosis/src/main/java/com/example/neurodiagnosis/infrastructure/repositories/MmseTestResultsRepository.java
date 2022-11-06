package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IMmseTestResultsRepository;
import com.example.neurodiagnosis.application.service.database.Database;
import com.example.neurodiagnosis.domain.entities.TestResult;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Named("mmseTestResultsRepository")
public class MmseTestResultsRepository implements IMmseTestResultsRepository, Serializable {
    @Override
    public boolean addNewTestResultsEntry(UUID userId, Date dateTimeOffset, int score) {
        try {
            TestResult testResult = new TestResult();
            testResult.setUserId(userId);
            testResult.setTestResult(score);
            testResult.setTestDate(dateTimeOffset);
            EntityManager entityManager = Database.getEntity();
            entityManager.getTransaction().begin();
            entityManager.persist(testResult);
            entityManager.getTransaction().commit();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
}
