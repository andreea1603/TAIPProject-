package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IMmseTestResultsRepository;
import com.example.neurodiagnosis.application.service.database.Database;
import com.example.neurodiagnosis.domain.entities.TestResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Date;
import java.util.UUID;

public class MmseTestResultsRepository implements IMmseTestResultsRepository {
    @Override
    public void addNewTestResultsEntry(UUID userId, Date dateTimeOffset, int score) {
        TestResult testResult = new TestResult();
        testResult.setUserId(userId);
        testResult.setTestResult(score);
        testResult.setTestDate(dateTimeOffset);
        EntityManagerFactory em = Database.getEntity();
        EntityManager entityManager = em.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(testResult);
        entityManager.getTransaction().commit();
    }
}
