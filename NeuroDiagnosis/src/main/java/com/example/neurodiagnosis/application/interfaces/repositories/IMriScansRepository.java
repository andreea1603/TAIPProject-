package com.example.neurodiagnosis.application.interfaces.repositories;

import com.example.neurodiagnosis.domain.entities.Mri;

import java.io.File;
import java.util.Date;
import java.util.UUID;

public interface IMriScansRepository {
    Mri addNewScanEntry(UUID usedId, File photo);
}
