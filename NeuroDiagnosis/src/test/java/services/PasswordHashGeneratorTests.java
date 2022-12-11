package services;

import com.example.neurodiagnosis.application.service.user.IPasswordHashGeneratorService;
import com.example.neurodiagnosis.application.service.user.PasswordHashGeneratorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PasswordHashGeneratorTests {

    public PasswordHashGeneratorTests() {

    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenPasswordHashGeneratorService__whenAlgIsSHA256__calculateHashShouldReturnCorrectSHA256Hash() {
        //ARRANGE
        IPasswordHashGeneratorService passwordHashGeneratorService = new PasswordHashGeneratorService();

        //ACT
        var hash = passwordHashGeneratorService.calculateHash("azur", "SHA-256");

        //ASSERT
        assertEquals("fdf024dbb567981d6c6defe35175450940c6e04cbb29addf30c65a27b7795bbb", hash);
    }


    @Test
    void givenPasswordHashGeneratorService__whenInvalidAlgorithmSupplied__calculateHashShouldReturnCorrectSHA256Hash() {
        //ARRANGE
        IPasswordHashGeneratorService passwordHashGeneratorService = new PasswordHashGeneratorService();


        //ASSERT
        assertThrows(RuntimeException.class,
                () -> passwordHashGeneratorService.calculateHash("azur", "algInexistent"));
    }
}