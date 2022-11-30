package webapi.controllers;

import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.application.service.user.PasswordHashGeneratorService;
import com.example.neurodiagnosis.application.service.user.UsersService;
import com.example.neurodiagnosis.application.service.validators.EmailValidatorService;
import com.example.neurodiagnosis.infrastructure.email.EmailService;
import com.example.neurodiagnosis.infrastructure.repositories.UserRepository;
import com.example.neurodiagnosis.webapi.controllers.AuthController;
import com.example.neurodiagnosis.webapi.dtos.LoginRequestDTO;
import com.example.neurodiagnosis.webapi.dtos.RegisterRequestDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthControllerTests {

    @Test
    public void givenAuthController__WhenRegisterIsRequestedWithInvalidData_ShouldRespondWithRegisterError() {
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
    public void givenAuthController__WhenLoginIsRequestedWithInvalidData_ShouldRespondWithLoginError() {

        //Arrange
        AuthController ac = new AuthController(new UsersService(new UserRepository(new DatabaseContextTests()),
                new EmailService(),
                new EmailValidatorService(),
                new PasswordHashGeneratorService()
        ));


        //ACT & ASSERT
        assertThrows(Exception.class, () -> ac.loginUser(new LoginRequestDTO()));
    }

}
