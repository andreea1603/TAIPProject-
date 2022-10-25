package com.example.neurodiagnosis.application.interfaces.repositories;

import com.example.neurodiagnosis.domain.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    User createUser(String username, String lastName, String firstName, String passwordHash);
    User updateUserPassword(UUID userId, String passwordHash);
    Optional<User> findByEmail(String email);
    void deleteUserAccount(UUID userId);
}