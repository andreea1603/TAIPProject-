package webapi.controllers;

import com.example.neurodiagnosis.application.service.mri.IMriScansService;
import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.webapi.controllers.MriScansController;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MriScansControllerTests {
    @Test
    void givenMriScansController__whenSubmitScanIsRequestedWithValidData__ShouldReturnOkStatus() throws URISyntaxException, IOException {
        //ARRANGE

        var mriScansService = Mockito.mock(IMriScansService.class);
        String mriToReturn="MidDementia: 92%";

        Mockito
                .when(mriScansService.submitMriScan(Matchers.any(), Matchers.any()))
                .thenReturn(mriToReturn);

        var mriScansController = new MriScansController(mriScansService);

        assertNotNull(mriScansController.submitMriScan( Mockito.mock(File.class)));
    }
}
