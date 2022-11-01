package com.example.neurodiagnosis.application.service.database;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {
    private static EntityManagerFactory entityManagerFactory;
    public static EntityManagerFactory getEntity(){
        if(entityManagerFactory == null){
           entityManagerFactory = Persistence.createEntityManagerFactory("NeuroDiagnosis");
        }
        return entityManagerFactory;
    }
    public static void closeEntity(EntityManagerFactory entityManagerFactory){
        entityManagerFactory.close();
    }
}
