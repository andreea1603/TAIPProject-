package com.example.neurodiagnosis.application.interfaces.repositories;

import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.infrastructure.repositories.base.IRepository;
import com.example.neurodiagnosis.webapi.dtos.RegisterRequestDTO;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends IRepository {
    User createUser(String username, String lastName, String firstName, String email, String passwordHash);
    User createUser(RegisterRequestDTO registerRequestDTO, String passwordHash);
    User updateUserPassword(UUID userId, String passwordHash);
    Optional<User> findById(UUID userId);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String userName);
    void deleteUserAccount(UUID userId);

    int getUsersCount();
}
