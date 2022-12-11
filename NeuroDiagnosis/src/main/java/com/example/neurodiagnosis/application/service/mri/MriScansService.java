package com.example.neurodiagnosis.application.service.mri;

import com.example.neurodiagnosis.application.interfaces.repositories.IMriScansRepository;
import com.example.neurodiagnosis.domain.entities.Mri;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;
@NoArgsConstructor
@Named("MriScansService")
@SessionScoped
public class MriScansService implements IMriScansService, Serializable {
    private IMriScansRepository mriScansRepository;

    @Inject
    public MriScansService(@Named("mriScansRepository") IMriScansRepository mriScansRepository) {
        this.mriScansRepository = mriScansRepository;
    }

    @Override
    public Mri submitMriScan(UUID usedId, File mineType) throws IOException {
        return mriScansRepository.addNewScanEntry(usedId, mineType);
    }
}
