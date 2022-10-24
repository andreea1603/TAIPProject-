package com.example.neurodiagnosis.service.user;

import com.example.neurodiagnosis.model.User;

import java.util.Optional;
import java.util.UUID;

public interface IUsersService {
    Optional<String> loginUser(String userNameEmail, String password);
    User registerUser(String username, String firstName, String lastName, String email, String password);
    void deleteAccount(UUID userId);
}
