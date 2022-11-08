package repositories;

import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.infrastructure.repositories.MriScansRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MriScansRepositoryTest {
    private final UUID USER_ID = new UUID(64, 64);
    private final Date DATE = new Date(2022, Calendar.MARCH, 2);
    private final String URL = "www.my.url";
    private final Date EXPIRES_AT = new Date(2022, Calendar.MARCH, 2);
    private final Date SIGNED_AT = new Date(2022, Calendar.MARCH, 2);
    private final String MINE_TYPE = "png";

    private Mri mri;

    public MriScansRepositoryTest() {

    }

    @BeforeEach
    void setUp() {
        mri = new Mri();
        mri.setUserId(USER_ID);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void addNewScanEntryUserIdTest() {
        var repository = new MriScansRepository(new DatabaseContextTests());
        Mri resultMri = repository.addNewScanEntry(USER_ID, DATE, URL, EXPIRES_AT, SIGNED_AT, MINE_TYPE);

        assertEquals(mri.getUserId(), resultMri.getUserId());

    }

    @Test
    void addNewScanEntryMriTest() {
        var repository = new MriScansRepository(new DatabaseContextTests());
        Mri resultMri = repository.addNewScanEntry(USER_ID, DATE, URL, EXPIRES_AT, SIGNED_AT, MINE_TYPE);

        assertEquals(mri, resultMri);
    }
}
