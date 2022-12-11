package com.example.neurodiagnosis.infrastructure.repositories.base;

import jakarta.persistence.EntityManager;

public interface IRepository {

    EntityManager getEntityManager();
}
