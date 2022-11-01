package com.example.neurodiagnosis.application.service.user;

import com.example.neurodiagnosis.application.service.validators.IEmailValidatorService;
import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.application.interfaces.email.IEmailService;
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
    private IUserRepository userRepository;
    private IEmailService emailService;
    private IEmailValidatorService emailValidatorService;
    private IPasswordHashGeneratorService passwordHashGeneratorService;

    //TODO: Adaugat nume la repository-uri (si email service) si injectat!!
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

        Optional<User> user = null;

        if (this.emailValidatorService.validateEmail(userNameOrEmail)) {
            //Logs in by email

            user = userRepository.findByEmail(userNameOrEmail);
        } else {
            //Logs in by username

            user = userRepository.findByUsername(userNameOrEmail);
        }

        if (!user.isPresent()) {
            return Optional.empty();
        }

        var userToLogin = user.get();

        var passwordHash = passwordHashGeneratorService.calculateHash(password);

        if (!userToLogin.getPasswordHash().equals(passwordHash)) {
            return Optional.empty();
        }

        return Optional.of(JwtService.createJWT(userToLogin, "javaApppUrl", "reactFeUrl"));
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

        var passwordHash = passwordHashGeneratorService.calculateHash(password);

        var newUser = userRepository.createUser(username, lastName, firstName, passwordHash);

        if (newUser == null) {
            return Optional.empty();
        }

        return Optional.of(newUser);
    }

    @Override
    public void deleteAccount(UUID userId) {
    }
}
