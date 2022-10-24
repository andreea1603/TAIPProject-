package com.example.neurodiagnosis.service.user;

import com.example.neurodiagnosis.model.User;
import com.example.neurodiagnosis.repository.user.IUserRepository;
import com.example.neurodiagnosis.service.email.IEmailService;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class UsersService implements  IUsersService{
    private IUserRepository userRepository;
    private IEmailService emailService;

    @Override
    public Optional<String> loginUser(String userNameEmail, String password) {
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
