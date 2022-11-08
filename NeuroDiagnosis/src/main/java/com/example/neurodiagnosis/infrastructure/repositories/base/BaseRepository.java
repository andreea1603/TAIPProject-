package com.example.neurodiagnosis.infrastructure.repositories.base;

import com.example.neurodiagnosis.application.service.database.IDatabaseContext;
import jakarta.persistence.EntityManager;

public class BaseRepository {
    protected EntityManager em;
    private IDatabaseContext databaseContext;

    public BaseRepository(IDatabaseContext databaseContext) {

        this.databaseContext = databaseContext;

        this.em = databaseContext.getEntity();
    }

}
