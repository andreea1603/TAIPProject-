<<<<<<<< HEAD:NeuroDiagnosis/src/main/java/com/example/neurodiagnosis/application/interfaces/repositories/IMriScansRepository.java
package com.example.neurodiagnosis.application.interfaces.repositories;
========
package com.example.neurodiagnosis.repository.mri.scan;
>>>>>>>> 9de704094db5e0275a416fbfb87c0aaa3746a5d9:NeuroDiagnosis/src/main/java/com/example/neurodiagnosis/repository/mri/scan/IMriScansRepository.java

import com.example.neurodiagnosis.domain.entities.Mri;

import java.util.Date;
import java.util.UUID;

public interface IMriScansRepository {
    Mri addNewScanEntry(UUID usedId, Date date, String url, Date expiresAt, Date signedAt, String mineType);
}
