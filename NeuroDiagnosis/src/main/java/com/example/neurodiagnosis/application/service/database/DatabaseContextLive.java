package com.example.neurodiagnosis.application.service.database;

import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@Named("DatabaseContextLive")
public class DatabaseContextLive implements IDatabaseContext {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager em;

    @Override
    public EntityManager getEntity() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory("NeuroDiagnosis");
            em = entityManagerFactory.createEntityManager();
        }
        return em;
    }

    public void closeEntity(EntityManager entityManager) {
        em.close();
        entityManagerFactory.close();
    }
}
