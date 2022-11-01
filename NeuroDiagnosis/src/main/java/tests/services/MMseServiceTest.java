package tests.services;

import com.example.neurodiagnosis.application.interfaces.email.IEmailService;
import com.example.neurodiagnosis.application.interfaces.repositories.IMmseTestResultsRepository;
import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.application.service.mmse.IMmseService;
import com.example.neurodiagnosis.application.service.mmse.MMseService;
import com.example.neurodiagnosis.application.service.user.IUsersService;
import com.example.neurodiagnosis.application.service.user.PasswordHashGeneratorService;
import com.example.neurodiagnosis.application.service.user.UsersService;
import com.example.neurodiagnosis.application.service.validators.EmailValidatorService;
import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.infrastructure.repositories.MmseTestResultsRepository;
import com.example.neurodiagnosis.infrastructure.repositories.MriScansRepository;
import com.example.neurodiagnosis.webapi.dtos.RegisterRequestDTO;
import jakarta.inject.Scope;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class MMseServiceTest {
    public MMseServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void TestResultsService_ShouldBe_Return_True() {
        //Arrange
        IMmseTestResultsRepository mmseTestResultsRepository =
                Mockito.mock(MmseTestResultsRepository.class);
        UUID userId = new UUID(8, 8);
        Date date = new Date(2022, Calendar.MARCH, 2);

        Mockito.when(mmseTestResultsRepository.addNewTestResultsEntry(userId, date, 84))
                .thenReturn(true);
        IMmseService mmseService = new MMseService(mmseTestResultsRepository);

        //Act
        boolean result = mmseService.submitTestResults(userId, date, 84);
        //Assert
        assertTrue(result);
    }

    @Test
    void TestResults_ShouldBe_Added_To_DB() {
        //Arrange
        IMmseTestResultsRepository mmseTestResultsRepository =
                new MmseTestResultsRepository();
        UUID userId = new UUID(8, 8);
        Date date = new Date(2022, Calendar.MARCH, 2);

        IMmseService mmseService = new MMseService(mmseTestResultsRepository);

        //Act
        boolean result = mmseService.submitTestResults(userId, date, 84);
        //Assert
        assertTrue(!result);
    }
}
