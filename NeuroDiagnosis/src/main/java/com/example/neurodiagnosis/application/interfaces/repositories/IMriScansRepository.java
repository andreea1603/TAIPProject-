package com.example.neurodiagnosis.application.interfaces.repositories;

import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.infrastructure.repositories.base.IRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public interface IMriScansRepository extends IRepository {
    String addNewScanEntry(UUID usedId, File photo) throws IOException;
    String getMlResults(byte[] picture) throws IOException;
}
