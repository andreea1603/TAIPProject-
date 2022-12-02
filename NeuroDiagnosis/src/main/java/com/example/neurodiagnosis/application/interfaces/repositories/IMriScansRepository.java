package com.example.neurodiagnosis.application.interfaces.repositories;

import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.infrastructure.repositories.base.IRepository;

import java.io.File;
import java.util.Date;
import java.util.UUID;

public interface IMriScansRepository extends IRepository {
    Mri addNewScanEntry(UUID usedId, File photo);
}
