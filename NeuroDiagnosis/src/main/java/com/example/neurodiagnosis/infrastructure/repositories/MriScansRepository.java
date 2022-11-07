package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IMriScansRepository;
import com.example.neurodiagnosis.application.service.database.Database;
import com.example.neurodiagnosis.domain.entities.Mri;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Named("mriScansRepository")
@SessionScoped
public class MriScansRepository implements IMriScansRepository, Serializable {
    @Override
    public Mri addNewScanEntry(UUID usedId, Date date, String url, Date expiresAt, Date signedAt, String mineType) {
        Mri mri = new Mri();
        mri.setUserId(usedId);
        EntityManager entityManager = Database.getEntity();
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        entityManager.persist(mri);
        entityManager.getTransaction().commit();
        return mri;
    }
}
