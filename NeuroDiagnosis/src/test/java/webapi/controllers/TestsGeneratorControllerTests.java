package webapi.controllers;

import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.application.service.test.TestGeneratorService;
import com.example.neurodiagnosis.infrastructure.repositories.QuestionRepository;
import com.example.neurodiagnosis.infrastructure.repositories.UserRepository;
import com.example.neurodiagnosis.infrastructure.seed.UsersFactory;
import com.example.neurodiagnosis.webapi.controllers.TestGeneratorController;
import com.example.neurodiagnosis.webapi.security.UserPrincipal;
import jakarta.ws.rs.core.SecurityContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestsGeneratorControllerTests {
    public TestsGeneratorControllerTests() {

    }

    @BeforeEach
    void seed() {
        new UsersFactory(new UserRepository(new DatabaseContextTests()))
                .seedTestData();
    }

    @Test
    public void givenTestController__WhenGenerateIsRequested__ShouldReturnSomeTest() {

        TestGeneratorController tgc = new TestGeneratorController(
                new TestGeneratorService(new UserRepository(new DatabaseContextTests()),
                        new QuestionRepository(new DatabaseContextTests()))
                );

        var userFromSeed = new UserRepository(new DatabaseContextTests()).findByUsername("User").get();
        UserPrincipal requestingUserPrincipal = new UserPrincipal(userFromSeed.getId(), userFromSeed.getUsername(),
                userFromSeed.getEmailAddress());

        tgc.securityContext = Mockito.mock(SecurityContext.class);

        Mockito.when(tgc.securityContext.getUserPrincipal())
                .thenReturn(requestingUserPrincipal);


        var response = tgc.getTestForUser();

        assertNotNull(response);
    }
}
