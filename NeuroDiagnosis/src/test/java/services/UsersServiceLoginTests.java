package services;

import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.application.service.user.IUsersService;
import com.example.neurodiagnosis.application.service.user.PasswordHashGeneratorService;
import com.example.neurodiagnosis.application.service.user.UsersService;
import com.example.neurodiagnosis.application.service.validators.EmailValidatorService;
import com.example.neurodiagnosis.infrastructure.email.EmailService;
import com.example.neurodiagnosis.infrastructure.repositories.UserRepository;
import com.example.neurodiagnosis.infrastructure.seed.UsersFactory;
import com.example.neurodiagnosis.webapi.dtos.LoginRequestDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UsersServiceLoginTests {

    UsersFactory usersFactory;

    public UsersServiceLoginTests() {
        this.usersFactory = new UsersFactory(new UserRepository(new DatabaseContextTests()));
    }

    @BeforeEach
    void setUp() {

        this.usersFactory.seedTestData();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void givenUserServiceLogin__whenUserRequestLoginAndPasswordIsIncorrect__shouldRejectLoginRequestAndReturnNoJwt() {
        //Arange
        IUsersService usersService = new UsersService(new UserRepository(new DatabaseContextTests()), new EmailService(), new EmailValidatorService(), new PasswordHashGeneratorService());

        var loginRequest = new LoginRequestDTO("emailexistent1@gmail.com", "parolaGresita");

        //Act
        var response = usersService.loginUser(loginRequest.userNameOrEmail, loginRequest.password);

        //Assert
        assertFalse(response.isPresent());
    }


    @Test
    void givenUserServiceLogin__whenUserRequestLoginByEmailAndPasswordIsCorrect__shouldAcceptLoginRequestAndReturnJwt() {
        //Arange
        IUsersService usersService = new UsersService(new UserRepository(new DatabaseContextTests()), new EmailService(), new EmailValidatorService(), new PasswordHashGeneratorService());

        var loginRequest = new LoginRequestDTO("emailexistent1@gmail.com", "someGibberish");

        //Act
        var jwt = usersService.loginUser(loginRequest.userNameOrEmail, loginRequest.password);

        //Assert
        assertTrue(jwt.isPresent());
    }

    @Test
    void givenUserServiceLogin__whenUserRequestLoginByUsernameAndPasswordIsCorrect__shouldAcceptLoginRequestAndReturnJwt() {
        //Arange
        IUsersService usersService = new UsersService(new UserRepository(new DatabaseContextTests()), new EmailService(), new EmailValidatorService(), new PasswordHashGeneratorService());

        var loginRequest = new LoginRequestDTO("User", "someGibberish");

        //Act
        var jwt = usersService.loginUser(loginRequest.userNameOrEmail, loginRequest.password);

        //Assert
        assertTrue(jwt.isPresent());
    }


    @Test
    void givenUserServiceLogin__whenUserRequestLoginAndUsernameOrEmailRegistrationIsInexistentInDB__shouldRejectLoginRequestAndReturnNoJwt() {
        //Arrange
        IUsersService usersService = new UsersService(new UserRepository(new DatabaseContextTests()), new EmailService(),
                new EmailValidatorService(), new PasswordHashGeneratorService());
        var loginRequest = new LoginRequestDTO("emailSauUsernameInexistentLaRegister@hotmail.com",
                "orice");

        //Act
        var response = usersService.loginUser(loginRequest.userNameOrEmail, loginRequest.password);

        //Assert
        assertFalse(response.isPresent());
    }
}