package com.example.neurodiagnosis.application.service.user;

import com.example.neurodiagnosis.application.interfaces.email.IEmailService;
import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.application.service.validators.IEmailValidatorService;
import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.webapi.dtos.RegisterRequestDTO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@NoArgsConstructor
@Named("usersService")
@SessionScoped
public class UsersService implements  IUsersService, Serializable {
    private static final String HASH_ALGORITHM = "SHA-256";
    private IUserRepository userRepository;
    private IEmailService emailService;
    private IEmailValidatorService emailValidatorService;
    private IPasswordHashGeneratorService passwordHashGeneratorService;

    @Inject
    public UsersService(@Named("userRepository") IUserRepository userRepository,
                        @Named("emailService") IEmailService emailService,
                        @Named("emailValidator")IEmailValidatorService emailValidatorService,
                        @Named("passwordHashGeneratorService") IPasswordHashGeneratorService passwordHashGeneratorService
                        ) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.emailValidatorService = emailValidatorService;
        this.passwordHashGeneratorService = passwordHashGeneratorService;
    }

    @Override
    public Optional<String> loginUser(String userNameOrEmail, String password) {

        Optional<User> user;

        if (this.emailValidatorService.validateEmail(userNameOrEmail)) {
            //Logs in by email

            user = userRepository.findByEmail(userNameOrEmail);
        } else {
            //Logs in by username

            user = userRepository.findByUsername(userNameOrEmail);
        }

        if (user.isEmpty()) {
            return Optional.empty();
        }

        var userToLogin = user.get();

        var passwordHash = passwordHashGeneratorService.calculateHash(password, HASH_ALGORITHM);

        if (!userToLogin.getPasswordHash().equals(passwordHash)) {
            return Optional.empty();
        }

        return Optional.of(JwtService.createJWT(userToLogin));
    }

    @Override
    public Optional<User> registerUser(String username, String firstName, String lastName, String email, String password) {
        if (!emailValidatorService.validateEmail(email)) {
            return Optional.empty();
        }

        Optional<User> existentUserByEmail = userRepository.findByEmail(email);
        Optional<User> existentUserByUsername = userRepository.findByUsername(username);

        if (existentUserByEmail.isPresent() || existentUserByUsername.isPresent()) {
            return Optional.empty();
        }

        if (password.length() < 8) {
            return Optional.empty();
        }

        var passwordHash = passwordHashGeneratorService.calculateHash(password, HASH_ALGORITHM);

        var newUser = userRepository.createUser(username, lastName, firstName, email, passwordHash);

        return Optional.of(newUser);
    }

    @Override
    public Optional<User> registerUser(RegisterRequestDTO registerRequestDTO) {
        if (!emailValidatorService.validateEmail(registerRequestDTO.getEmailAddress())) {
            return Optional.empty();
        }

        Optional<User> existentUserByEmail = userRepository.findByEmail(registerRequestDTO.getEmailAddress());
        Optional<User> existentUserByUsername = userRepository.findByUsername(registerRequestDTO.getUsername());



        if (existentUserByEmail.isPresent() || existentUserByUsername.isPresent()) {
            return Optional.empty();
        }

        if (registerRequestDTO.getPassword().length() < 8) {
            return Optional.empty();
        }

        var passwordHash = passwordHashGeneratorService.calculateHash(registerRequestDTO.getPassword(), HASH_ALGORITHM);

        var newUser = userRepository.createUser(registerRequestDTO, passwordHash);

        return Optional.of(newUser);
    }

    @Override
    public void deleteAccount(UUID userId) {
        // Not needed
    }
}
