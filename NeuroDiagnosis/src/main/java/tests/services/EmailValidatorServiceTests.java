package tests.services;

import com.example.neurodiagnosis.application.service.user.IUsersService;
import com.example.neurodiagnosis.application.service.user.UsersService;
import com.example.neurodiagnosis.application.service.validators.EmailValidatorService;
import com.example.neurodiagnosis.application.service.validators.IEmailValidatorService;
import com.example.neurodiagnosis.infrastructure.email.EmailService;
import com.example.neurodiagnosis.infrastructure.repositories.UserRepository;
import com.example.neurodiagnosis.webapi.dtos.LoginRequestDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;

class EmailValidatorServiceTests {

    public EmailValidatorServiceTests() {

    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    ///Login
    @ParameterizedTest
    @ValueSource(strings = {
            "plainaddress",
            "#@%^%#$@#$@#.com",
            "@example.com",
            "Joe Smith <email@example.com>",
            "email.example.com",
            "email@example@example.com",
            ".email@example.com",
            "email.@example.com",
            "email..email@example.com",
            "あいうえお@example.com",
            "email@example.com (Joe Smith)",
            "email@example",
            "email@-example.com",
            "email@example.web",
            "email@111.222.333.44444",
            "email@example..com",
            "Abc..123@example.com"
    })
    void givenEmailValidationService__whenInvalidSyntaxEmailIsPassed__shouldReturnFalse(String email) {
        //ARRANGE
        IEmailValidatorService emailValidatorService = new EmailValidatorService();

        //ACT
        var result = emailValidatorService.validateEmail(email);

        //ASSERT
        assertFalse(result);
    }
}