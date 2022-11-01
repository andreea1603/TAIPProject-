package com.example.neurodiagnosis.application.service.user;

import com.example.neurodiagnosis.application.service.validators.IEmailValidatorService;
import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.application.interfaces.email.IEmailService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
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

    //TODO: Adaugat nume la repository-uri (si email service) si injectat!!
    @Inject
    public UsersService(@Named("userRepository") IUserRepository userRepository,
                        @Named("emailService") IEmailService emailService,
                        @Named("emailValidator")IEmailValidatorService emailValidatorService
                        ) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.emailValidatorService = emailValidatorService;
    }

    @Override
    public Optional<String> loginUser(String userNameOrEmail, String password) {
        return Optional.of("");
    }

    @Override
    public Optional<User> registerUser(String username, String firstName, String lastName, String email, String password) {
        return Optional.of(new User(UUID.randomUUID()));
    }

    @Override
    public void deleteAccount(UUID userId) {
    }
}
