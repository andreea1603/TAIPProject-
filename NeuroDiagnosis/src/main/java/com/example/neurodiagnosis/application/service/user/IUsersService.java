package com.example.neurodiagnosis.application.service.user;

import com.example.neurodiagnosis.domain.entities.User;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface IUsersService {
    Optional<String> loginUser(String userNameOrEmail, String password);
    Optional<User> registerUser(String username, String firstName, String lastName, String email, String password);
    void deleteAccount(UUID userId);
}
