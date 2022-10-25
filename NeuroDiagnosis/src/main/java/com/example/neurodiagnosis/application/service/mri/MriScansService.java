package com.example.neurodiagnosis.application.service.mri;

<<<<<<< HEAD:NeuroDiagnosis/src/main/java/com/example/neurodiagnosis/application/service/mri/MriScansService.java
import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.application.interfaces.repositories.IMriScansRepository;
=======
import com.example.neurodiagnosis.model.Mri;
import com.example.neurodiagnosis.repository.mri.scan.IMriScansRepository;
>>>>>>> 9de704094db5e0275a416fbfb87c0aaa3746a5d9:NeuroDiagnosis/src/main/java/com/example/neurodiagnosis/service/mri/MriScansService.java

import java.util.Date;
import java.util.UUID;

public class MriScansService implements IMriScansService {
    private IMriScansRepository mriScansRepository;

    @Override
    public Mri submitMriScan(UUID usedId, Date date, String url, Date expiresAt, Date signedAt, String mineType) {
        return null;
    }
}
