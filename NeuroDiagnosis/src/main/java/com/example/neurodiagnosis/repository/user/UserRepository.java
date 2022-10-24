package com.example.neurodiagnosis.repository.user;

import com.example.neurodiagnosis.model.User;

import java.util.Optional;
import java.util.UUID;

public class UserRepository implements IUserRepository{


    @Override
    public User createUser(String username, String lastName, String firstName, String passwordHash) {
        return null;
    }

    @Override
    public User updateUserPassword(UUID userId, String passwordHash) {
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public void deleteUserAccount(UUID userId) {

    }
}
