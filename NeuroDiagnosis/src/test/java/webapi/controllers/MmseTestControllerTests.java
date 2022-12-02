package webapi.controllers;

import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.application.service.mmse.MMseService;
import com.example.neurodiagnosis.application.service.question.QuestionService;
import com.example.neurodiagnosis.infrastructure.repositories.MmseTestResultsRepository;
import com.example.neurodiagnosis.infrastructure.repositories.QuestionRepository;
import com.example.neurodiagnosis.infrastructure.repositories.UserRepository;
import com.example.neurodiagnosis.infrastructure.seed.UsersFactory;
import com.example.neurodiagnosis.webapi.controllers.MmseTestsController;
import com.example.neurodiagnosis.webapi.controllers.QuestionGeneratorController;
import com.example.neurodiagnosis.webapi.dtos.SubmitTestResultRequestDTO;
import com.example.neurodiagnosis.webapi.security.UserPrincipal;
import jakarta.ws.rs.core.SecurityContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MmseTestControllerTests {

    @BeforeEach
    void seed() {
        new UsersFactory(new UserRepository(new DatabaseContextTests()))
                .seedTestData();
    }

    @Test
    public void givenMmseController__WhenSubmitRequestedWithValidData__ShouldThrow() {

        //ARRANGE
        var userFromSeed = new UserRepository(new DatabaseContextTests())
                .findByUsername("User").get();

        UserPrincipal requestingUserPrincipal = new UserPrincipal(userFromSeed.getId(), userFromSeed.getUsername(),
                userFromSeed.getEmailAddress());


        var mmseController = new MmseTestsController(new MMseService(new MmseTestResultsRepository(new DatabaseContextTests())));

        mmseController.securityContext = Mockito.mock(SecurityContext.class);

        Mockito.when(mmseController.securityContext.getUserPrincipal())
                .thenReturn(requestingUserPrincipal);
        //ACT & ASSERT

        assertNotNull(mmseController.submitTestsResults(new SubmitTestResultRequestDTO(10, new Date(), userFromSeed.getId())));
    }
}
