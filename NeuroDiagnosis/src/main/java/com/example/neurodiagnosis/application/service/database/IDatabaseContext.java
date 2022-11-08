package com.example.neurodiagnosis.application.service.database;

import jakarta.persistence.EntityManager;

import java.io.Serializable;

public interface IDatabaseContext extends Serializable {
    public EntityManager getEntity();
}
