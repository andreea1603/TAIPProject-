package com.example.neurodiagnosis.infrastructure.repositories.base;

import jakarta.persistence.EntityManager;

public interface IRepository {

    public EntityManager getEntityManager();
}
