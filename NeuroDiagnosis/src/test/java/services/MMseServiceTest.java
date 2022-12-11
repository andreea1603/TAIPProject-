package services;

import com.example.neurodiagnosis.application.interfaces.repositories.IMmseTestResultsRepository;
import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.application.service.mmse.IMmseService;
import com.example.neurodiagnosis.application.service.mmse.MMseService;
import com.example.neurodiagnosis.domain.entities.TestResult;
import com.example.neurodiagnosis.infrastructure.repositories.MmseTestResultsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
                .thenReturn(new TestResult(UUID.randomUUID(), date, 84, userId));
        IMmseService mmseService = new MMseService(mmseTestResultsRepository);

        //Act
        TestResult result = mmseService.submitTestResults(userId, date, 84);
        //Assert
        assertNotNull(result);
    }

    @Test
    void TestResults_ShouldBe_Added_To_DB() {
        //Arrange
        IMmseTestResultsRepository mmseTestResultsRepository =
                new MmseTestResultsRepository(new DatabaseContextTests());
        UUID userId = UUID.randomUUID();
        Date date = new Date(2022, Calendar.MARCH, 2);

        IMmseService mmseService = new MMseService(mmseTestResultsRepository);

        //Act
        TestResult result = mmseService.submitTestResults(userId, date, 84);
        //Assert
        assertNotNull(result);
    }
}
