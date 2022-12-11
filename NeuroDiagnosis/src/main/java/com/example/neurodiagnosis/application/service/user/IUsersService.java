package com.example.neurodiagnosis.application.service.user;

import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.webapi.dtos.RegisterRequestDTO;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

public interface IUsersService {
    Optional<String> loginUser(String userNameOrEmail, String password);
    Optional<User> registerUser(String username, String firstName, String lastName, String email, String password);
    Optional<User> registerUser(RegisterRequestDTO registerRequestDTO);

    void deleteAccount(UUID userId);
}
