package com.example.neurodiagnosis.application.service.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Database {
    private static EntityManagerFactory entityManagerFactory;
    private static  EntityManager em;
    public static EntityManager getEntity(){
        if(entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("NeuroDiagnosis");
            em = entityManagerFactory.createEntityManager();
        }
        return em;
    }
    public static void closeEntity(EntityManager entityManager){
        em.close();
        entityManagerFactory.close();
    }
}
