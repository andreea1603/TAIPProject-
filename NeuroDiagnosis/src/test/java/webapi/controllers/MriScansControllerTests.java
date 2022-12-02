package webapi.controllers;

import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.application.service.mri.IMriScansService;
import com.example.neurodiagnosis.application.service.mri.MriScansService;
import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.infrastructure.repositories.MriScansRepository;
import com.example.neurodiagnosis.webapi.controllers.MriScansController;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MriScansControllerTests {
    @Test
    public void givenMriScansController__whenSubmitScanIsRequestedWithValidData__ShouldReturnOkStatus() throws URISyntaxException {
        //ARRANGE

        var mriScansService = Mockito.mock(IMriScansService.class);
        var mriToReturn = new Mri();

        mriToReturn.setImage(new byte[8]);
        mriToReturn.setUserId(UUID.randomUUID());

        Mockito
                .when(mriScansService.submitMriScan(Matchers.any(), Matchers.any()))
                .thenReturn(mriToReturn);

        var mriScansController = new MriScansController(mriScansService);

        assertNotNull(mriScansController.submitMriScan(UUID.randomUUID().toString(), Mockito.mock(File.class)));
    }
}
