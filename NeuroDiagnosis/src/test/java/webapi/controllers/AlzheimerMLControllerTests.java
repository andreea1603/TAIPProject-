package webapi.controllers;

import com.example.neurodiagnosis.application.service.mripredict.AlzheimerPredictorFromMriScanService;
import com.example.neurodiagnosis.webapi.controllers.AlzheimerMLController;
import com.example.neurodiagnosis.webapi.dtos.MriScanDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AlzheimerMLControllerTests {
    @Test
    void givenAuthController__WhenRegisterIsRequestedWithInvalidData_ShouldRespondWithRegisterError() {
        //Arrange
        AlzheimerMLController amc = new AlzheimerMLController(new AlzheimerPredictorFromMriScanService());

        //ACT & ASSERT
        assertNotNull(amc.predictAlzheimers(new MriScanDTO()));
    }
}
