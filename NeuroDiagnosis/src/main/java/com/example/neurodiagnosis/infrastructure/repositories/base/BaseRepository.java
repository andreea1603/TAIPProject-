package com.example.neurodiagnosis.infrastructure.repositories.base;

import com.example.neurodiagnosis.application.service.database.IDatabaseContext;
import jakarta.persistence.EntityManager;
import lombok.Getter;

@Getter
public class BaseRepository implements IRepository{
    protected EntityManager em;
    private IDatabaseContext databaseContext;

    public BaseRepository(IDatabaseContext databaseContext) {

        this.databaseContext = databaseContext;

        this.em = databaseContext.getEntity();
    }

    @Override
    public EntityManager getEntityManager() {
        return this.em;
    }
}
