package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IMriScansRepository;
import com.example.neurodiagnosis.application.service.database.IDatabaseContext;
import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.infrastructure.repositories.base.BaseRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
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
    public Mri addNewScanEntry(UUID usedId, File photo){
        Mri mri = new Mri();
        mri.setUserId(usedId);
        mri.setId(UUID.randomUUID());

        try {
            mri.setImage(Files.readAllBytes(photo.toPath()));
        }catch (Exception e){
            return null;
        }

        //TODO: MRI posibil remote service?
        try {

            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.persist(mri);
            em.getTransaction().commit();
            return mri;

        } catch (Exception e){
            return null;
        }
    }
}
