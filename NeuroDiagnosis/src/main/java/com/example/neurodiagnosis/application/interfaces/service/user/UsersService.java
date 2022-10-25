package com.example.neurodiagnosis.application.interfaces.service.user;

import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.application.interfaces.email.IEmailService;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class UsersService implements  IUsersService{
    private IUserRepository userRepository;
    private IEmailService emailService;

    @Override
    public Optional<String> loginUser(String userNameOrEmail, String password) {
        return Optional.empty();
    }

    @Override
    public User registerUser(String username, String firstName, String lastName, String email, String password) {
        return null;
    }

    @Override
    public void deleteAccount(UUID userId) {
    }
}
