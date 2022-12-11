package webapi.controllers;

import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.application.service.user.PasswordHashGeneratorService;
import com.example.neurodiagnosis.application.service.user.UsersService;
import com.example.neurodiagnosis.application.service.validators.EmailValidatorService;
import com.example.neurodiagnosis.infrastructure.email.EmailService;
import com.example.neurodiagnosis.infrastructure.repositories.UserRepository;
import com.example.neurodiagnosis.infrastructure.seed.UsersFactory;
import com.example.neurodiagnosis.webapi.controllers.AuthController;
import com.example.neurodiagnosis.webapi.dtos.LoginRequestDTO;
import com.example.neurodiagnosis.webapi.dtos.RegisterRequestDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthControllerTests {


    @BeforeEach
    void setUp() {



        new UsersFactory(new UserRepository(new DatabaseContextTests())).seedTestData();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenAuthController__WhenRegisterIsRequestedWithInvalidData_ShouldRespondWithRegisterError() {
        //Arrange
        AuthController ac = new AuthController(new UsersService(new UserRepository(new DatabaseContextTests()),
                new EmailService(),
                new EmailValidatorService(),
                new PasswordHashGeneratorService()
        ));

        //ACT & ASSERT
        assertThrows(Exception.class, () -> ac.registerUser(new RegisterRequestDTO()));
    }


    @Test
    void givenAuthController__WhenRegisterIsRequestedWithProperData_ShouldRespondWithRegistrationOkStatus() throws Exception {
        //Arrange
        AuthController ac = new AuthController(new UsersService(new UserRepository(new DatabaseContextTests()),
                new EmailService(),
                new EmailValidatorService(),
                new PasswordHashGeneratorService()
        ));

        var registerRequest = new RegisterRequestDTO("zero63055@gmail.com",
                "nicu19999",
                "Nicu",
                "P.",
                "passw0rd",
                "074244424498");

        //ACT
        var result = ac.registerUser(registerRequest);

        //ASSERT

        assertNotNull(result);
    }

    @Test
    void givenAuthController__WhenLoginIsRequestedWithInvalidData_ShouldRespondWithLoginError() {

        //Arrange
        AuthController ac = new AuthController(new UsersService(new UserRepository(new DatabaseContextTests()),
                new EmailService(),
                new EmailValidatorService(),
                new PasswordHashGeneratorService()
        ));


        //ACT & ASSERT
        assertThrows(Exception.class, () -> ac.loginUser(new LoginRequestDTO()));
    }


    @Test
    void givenAuthController__WhenLoginIsRequestedWithProperData_ShouldRespondWithJwtAndOkStatus() throws Exception {
        //Arrange
        AuthController ac = new AuthController(new UsersService(new UserRepository(new DatabaseContextTests()),
                new EmailService(),
                new EmailValidatorService(),
                new PasswordHashGeneratorService()
        ));

        //User luat din seed - UsersFactory
        var loginRequest = new LoginRequestDTO("User",
                "someGibberish");

        //ACT
        var result = ac.loginUser(loginRequest);

        //ASSERT

        assertNotNull(result);
    }

}
