package com.example.neurodiagnosis.repository.user;

import com.example.neurodiagnosis.model.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    User createUser(String username, String lastName, String firstName, String passwordHash);
    User updateUserPassword(UUID userId, String passwordHash);
    Optional<User> findByEmail(String email);
    void deleteUserAccount(UUID userId);
}
