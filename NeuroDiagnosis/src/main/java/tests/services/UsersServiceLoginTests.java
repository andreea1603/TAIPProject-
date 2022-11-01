package tests.services;

import com.example.neurodiagnosis.application.service.user.IUsersService;
import com.example.neurodiagnosis.application.service.user.PasswordHashGeneratorService;
import com.example.neurodiagnosis.application.service.user.UsersService;
import com.example.neurodiagnosis.application.service.validators.EmailValidatorService;
import com.example.neurodiagnosis.infrastructure.email.EmailService;
import com.example.neurodiagnosis.infrastructure.repositories.UserRepository;
import com.example.neurodiagnosis.webapi.dtos.LoginRequestDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsersServiceLoginTests {

    public UsersServiceLoginTests() {
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    ///Login
    @Test
    void givenUserServiceLogin__whenUserRequestLoginAndPasswordIsIncorrect__shouldRejectLoginRequestAndReturnNoJwt() {
        //Arange
        IUsersService usersService = new UsersService(new UserRepository(), new EmailService(), new EmailValidatorService(), new PasswordHashGeneratorService());

        var loginRequest = new LoginRequestDTO("emailExistentLaRegister@hotmail.com", "parolaGresita");

        //Act
        var response = usersService.loginUser(loginRequest.userNameOrEmail, loginRequest.password);

        //Assert
        assertFalse(response.isPresent());
    }


    @Test
    void givenUserServiceLogin__whenUserRequestLoginAndUsernameOrEmailRegistrationIsInexistentInDB__shouldRejectLoginRequestAndReturnNoJwt() {
        //Arrange
        IUsersService usersService = new UsersService(new UserRepository(), new EmailService(), new EmailValidatorService(), new PasswordHashGeneratorService());
        var loginRequest = new LoginRequestDTO("emailSauUsernameInexistentLaRegister@hotmail.com", "orice");

        //Act
        var response = usersService.loginUser(loginRequest.userNameOrEmail, loginRequest.password);

        //Assert
        assertFalse(response.isPresent());
    }
}