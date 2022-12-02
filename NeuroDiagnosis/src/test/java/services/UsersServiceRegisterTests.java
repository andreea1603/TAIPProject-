package services;

import com.example.neurodiagnosis.application.interfaces.email.IEmailService;
import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.application.service.user.IUsersService;
import com.example.neurodiagnosis.application.service.user.PasswordHashGeneratorService;
import com.example.neurodiagnosis.application.service.user.UsersService;
import com.example.neurodiagnosis.application.service.validators.EmailValidatorService;
import com.example.neurodiagnosis.application.service.validators.IEmailValidatorService;
import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.infrastructure.repositories.UserRepository;
import com.example.neurodiagnosis.infrastructure.seed.UsersFactory;
import com.example.neurodiagnosis.webapi.dtos.RegisterRequestDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UsersServiceRegisterTests {

    public UsersServiceRegisterTests() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void clear() {
        new UsersFactory(new UserRepository(new DatabaseContextTests())).clearData();
    }

    @Test
    void givenUserServiceRegister__whenUserRequestRegisterAndEmailAreValidAndUserDoesNotExist__shouldRegisterUser() {
        //Arrange

        IEmailService emailServiceMock = Mockito.mock(IEmailService.class);
        Mockito.doNothing().when(emailServiceMock).sendTemplatedEmail(Matchers.any(),
                Matchers.any(), Matchers.any(), Matchers.any());

        IUserRepository usersRepositoryMock = Mockito.mock(IUserRepository.class);

        var registerRequest = new RegisterRequestDTO("email@gmail.com", "usernameNou",
                "firstName", "lastName", "password", "07777777777");

        //Make it return no existent user with same email / username.
        Mockito.doReturn(Optional.empty())
                .when(usersRepositoryMock)
                .findByEmail(registerRequest.getEmailAddress());
        Mockito.doReturn(Optional.empty())
                .when(usersRepositoryMock)
                .findByUsername(registerRequest.getUsername());

        //Returns new user on create
        Mockito.doReturn(new User(registerRequest.getEmailAddress(), registerRequest.getFirstName(),
                registerRequest.getUsername(), registerRequest.getLastName(), UUID.randomUUID()))
                .when(usersRepositoryMock).createUser(Matchers.any(), Matchers.any(),
                        Matchers.any(), Matchers.any(),Matchers.any());

        IUsersService usersService = new UsersService(usersRepositoryMock, emailServiceMock,
                new EmailValidatorService(), new PasswordHashGeneratorService());

        //Act
        var response = usersService.registerUser(registerRequest.getUsername(),
                registerRequest.getLastName(),
                registerRequest.getFirstName(),
                registerRequest.getEmailAddress(),
                registerRequest.getPassword());

        //Assert
        assertTrue(response.isPresent());
        assertEquals(response.get().getEmailAddress(), registerRequest.getEmailAddress());
    }


    @Test
    void givenUserServiceRegister__whenUserRequestRegisterAndEmailIsInvalid__shouldNotRegisterUser() {
        //Arrange

        var registerRequest = new RegisterRequestDTO("emailInvalid.com", "usernameNou",
                "firstName", "lastName", "password", "07777777");

        IEmailValidatorService emailValidatorServiceMock = Mockito.mock(IEmailValidatorService.class);
        IEmailService emailServiceMock = Mockito.mock(IEmailService.class);

        Mockito.doNothing().when(emailServiceMock)
                .sendTemplatedEmail(Matchers.any(), Matchers.any(), Matchers.any(), Matchers.any());

        Mockito.doReturn(false).when(emailValidatorServiceMock)
                .validateEmail(registerRequest.getEmailAddress());

        IUsersService usersService = new UsersService(new UserRepository(new DatabaseContextTests()),
                emailServiceMock, emailValidatorServiceMock, new PasswordHashGeneratorService());

        //Act
        var response = usersService.registerUser(registerRequest.getUsername(),
                registerRequest.getLastName(),
                registerRequest.getFirstName(),
                registerRequest.getEmailAddress(),
                registerRequest.getPassword());

        //Assert
        assertFalse(response.isPresent());
    }


    @Test
    void givenUserServiceRegister__whenUserRequestRegisterAndPasswordTooSmall__shouldNotRegisterUser() {
        //Arrange

        var registerRequest = new RegisterRequestDTO("emailInvalid@gmail.com", "usernameNou",
                "firstName", "lastName", "shortpw", "07777777");

        IEmailValidatorService emailValidatorServiceMock = new EmailValidatorService();

        IEmailService emailServiceMock = Mockito.mock(IEmailService.class);
        Mockito.doNothing().when(emailServiceMock)
                .sendTemplatedEmail(Matchers.any(), Matchers.any(), Matchers.any(), Matchers.any());

        IUsersService usersService = new UsersService(new UserRepository(new DatabaseContextTests()),
                emailServiceMock, emailValidatorServiceMock, new PasswordHashGeneratorService());

        //Act
        var response = usersService.registerUser(registerRequest.getUsername(),
                registerRequest.getLastName(),
                registerRequest.getFirstName(),
                registerRequest.getEmailAddress(),
                registerRequest.getPassword());

        //Assert
        assertFalse(response.isPresent());
    }

    @Test
    void givenUserServiceRegister__whenUserRequestRegisterAndEmailIsTaken__shouldNotRegisterUser() {
        //Arange
        IUserRepository usersRepositoryMock = Mockito.mock(IUserRepository.class);
        IEmailService emailServiceMock = Mockito.mock(IEmailService.class);

        var registerRequest = new RegisterRequestDTO("emailInvalid.com", "usernameNou",
                "firstName", "lastName", "password", "077777777");


        Mockito
                .when(usersRepositoryMock.findByEmail(Matchers.any()))
                        .thenReturn(Optional.of(new User(UUID.randomUUID())));

        Mockito
                .when(usersRepositoryMock.findByUsername(Matchers.any()))
                .thenReturn(Optional.of(new User(UUID.randomUUID())));


        Mockito.doNothing()
                .when(emailServiceMock)
                .sendTemplatedEmail(Matchers.any(), Matchers.any(), Matchers.any(), Matchers.any());

        IUsersService usersService = new UsersService(usersRepositoryMock, emailServiceMock, new EmailValidatorService(), new PasswordHashGeneratorService());

        //Act
        var response = usersService.registerUser(registerRequest.getUsername(),
                registerRequest.getLastName(),
                registerRequest.getFirstName(),
                registerRequest.getEmailAddress(),
                registerRequest.getPassword());

        //Assert
        assertFalse(response.isPresent());
    }


}