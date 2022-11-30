package services;

import com.example.neurodiagnosis.application.service.user.IPasswordHashGeneratorService;
import com.example.neurodiagnosis.application.service.user.PasswordHashGeneratorService;
import com.example.neurodiagnosis.application.service.validators.EmailValidatorService;
import com.example.neurodiagnosis.application.service.validators.IEmailValidatorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(hash, "fdf024dbb567981d6c6defe35175450940c6e04cbb29addf30c65a27b7795bbb");
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