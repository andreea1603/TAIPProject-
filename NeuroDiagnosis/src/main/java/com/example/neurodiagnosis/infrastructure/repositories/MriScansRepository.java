package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IMriScansRepository;
import com.example.neurodiagnosis.application.service.database.Database;
import com.example.neurodiagnosis.domain.entities.Mri;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Date;
import java.util.UUID;

public class MriScansRepository implements IMriScansRepository {
    @Override
    public Mri addNewScanEntry(UUID usedId, Date date, String url, Date expiresAt, Date signedAt, String mineType) {
        Mri mri = new Mri();
        mri.setUserId(usedId);
        EntityManagerFactory em = Database.getEntity();
        EntityManager entityManager = em.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(mri);
        entityManager.getTransaction().commit();
        return mri;
    }
}
