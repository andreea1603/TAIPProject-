package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.application.service.database.Database;
import com.example.neurodiagnosis.domain.entities.User;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

@Named("userRepository")
@SessionScoped
public class UserRepository implements IUserRepository, Serializable {

    @Override
    public User createUser(String username, String lastName, String firstName, String passwordHash) {
        User user = new User();
        user.setUsername(username);
        user.setLastName(lastName);
        user.setPasswordHash(passwordHash);
        EntityManagerFactory em = Database.getEntity();
        EntityManager entityManager = em.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

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
    public Optional<User> findByUsername(String userName) {
        return Optional.empty();
    }

    @Override
    public void deleteUserAccount(UUID userId) {

    }
}
