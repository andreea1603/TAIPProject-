package services;

import com.example.neurodiagnosis.application.service.mri.MriScansService;
import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.infrastructure.repositories.MriScansRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MriScansServiceTest {
    private final UUID USER_ID = new UUID(64, 64);
    private final Date DATE = new Date(2022, Calendar.MARCH, 2);
    private final String URL = "www.my.url";
    private final Date EXPIRES_AT = new Date(2022, Calendar.MARCH, 2);
    private final Date SIGNED_AT = new Date(2022, Calendar.MARCH, 2);
    private final File MINE_TYPE = new File("");


    public MriScansServiceTest() {

    }

    private MriScansService mriScansService;

    @BeforeEach
    void setUp() throws IOException {
        String mri = "{MidDementia: 92%, Dementia: 1%}";

        MriScansRepository repository = Mockito.mock(MriScansRepository.class);
        Mockito.when(repository.addNewScanEntry(USER_ID, MINE_TYPE))
                .thenReturn(mri);

        mriScansService = new MriScansService(repository);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addNewScanEntryTest() throws IOException {
        String mri = mriScansService.submitMriScan(USER_ID,  MINE_TYPE);

        assertEquals(mri, "{MidDementia: 92%, Dementia: 1%}");
    }
}
