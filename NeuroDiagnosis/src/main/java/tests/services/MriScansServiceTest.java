package tests.services;

import com.example.neurodiagnosis.application.service.mri.MriScansService;
import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.infrastructure.repositories.MriScansRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


public class MriScansServiceTest {
    private final UUID USER_ID = new UUID(64, 64);
    private final Date DATE = new Date(2022, Calendar.MARCH, 2);
    private final String URL = "www.my.url";
    private final Date EXPIRES_AT = new Date(2022, Calendar.MARCH, 2);
    private final Date SIGNED_AT = new Date(2022, Calendar.MARCH, 2);
    private final String MINE_TYPE = "png";


    public MriScansServiceTest() {

    }

    private MriScansService mriScansService;

    @BeforeEach
    void setUp() {
        Mri mri = new Mri();
        mri.setUserId(USER_ID);

        MriScansRepository repository = Mockito.mock(MriScansRepository.class);
        Mockito.when(repository.addNewScanEntry(USER_ID, DATE, URL, EXPIRES_AT, SIGNED_AT, MINE_TYPE))
                .thenReturn(mri);

        mriScansService = new MriScansService(repository);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addNewScanEntryTest() {
        Mri mri = mriScansService.submitMriScan(USER_ID, DATE, URL, EXPIRES_AT, SIGNED_AT, MINE_TYPE);

        assertEquals(mri.getUserId(), USER_ID);
    }
}
