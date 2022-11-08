package com.example.neurodiagnosis.application.service.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseContextTests implements IDatabaseContext{

    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;
    @Override
    public EntityManager getEntity() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("NeuroDiagnosisTests");
            em = entityManagerFactory.createEntityManager();
        }
        return em;
    }

    public void closeEntity(EntityManager entityManager) {
        em.close();
        entityManagerFactory.close();
    }
}
