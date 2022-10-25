package com.example.neurodiagnosis.application.service.user;

import com.example.neurodiagnosis.domain.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface IUsersService {
    Optional<String> loginUser(String userNameOrEmail, String password);
    User registerUser(String username, String firstName, String lastName, String email, String password);
    void deleteAccount(UUID userId);
}
