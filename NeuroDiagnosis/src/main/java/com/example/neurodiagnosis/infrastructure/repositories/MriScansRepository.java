package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IMriScansRepository;
import com.example.neurodiagnosis.application.service.database.DatabaseContextLive;
import com.example.neurodiagnosis.application.service.database.IDatabaseContext;
import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.infrastructure.repositories.base.BaseRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Named("mriScansRepository")
@SessionScoped
public class MriScansRepository extends BaseRepository
        implements IMriScansRepository, Serializable {
    @Inject
    public MriScansRepository(@Named("DatabaseContextLive") IDatabaseContext databaseContext) {
        super(databaseContext);
    }

    @Override
    public Mri addNewScanEntry(UUID usedId, Date date, String url, Date expiresAt, Date signedAt, String mineType) {
        Mri mri = new Mri();
        mri.setUserId(usedId);

        //TODO: MRI posibil remote service?

        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
        em.persist(mri);
        em.getTransaction().commit();
        return mri;
    }
}
