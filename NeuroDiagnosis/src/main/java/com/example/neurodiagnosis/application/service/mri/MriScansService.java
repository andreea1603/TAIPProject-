package com.example.neurodiagnosis.application.service.mri;

import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.application.interfaces.repositories.IMriScansRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.Date;
import java.util.UUID;

public class MriScansService implements IMriScansService {
    private final IMriScansRepository mriScansRepository;

    @Inject
    public MriScansService(@Named("mriScansRepository") IMriScansRepository mriScansRepository) {
        this.mriScansRepository = mriScansRepository;
    }

    @Override
    public Mri submitMriScan(UUID usedId, Date date, String url, Date expiresAt, Date signedAt, String mineType) {
        return mriScansRepository.addNewScanEntry(usedId, date, url, expiresAt, signedAt, mineType);
    }
}
