package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.application.service.database.Database;
import com.example.neurodiagnosis.domain.entities.User;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;

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
        user.setFirstName(firstName);
        user.setPasswordHash(passwordHash);
        EntityManager entityManager = Database.getEntity();
        if(!entityManager.getTransaction().isActive()){
            entityManager.getTransaction().begin();
        }
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User updateUserPassword(UUID userId, String passwordHash) {
        EntityManager entityManager = Database.getEntity();
        return null;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        EntityManager entityManager = Database.getEntity();
        User user = entityManager.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email)
                .getSingleResult();
        return Optional.of(user);

    }

    @Override
    public Optional<User> findByUsername(String userName) {
        EntityManager entityManager = Database.getEntity();
        User user = entityManager.createNamedQuery("User.findByUsername", User.class)
                .setParameter("username", userName)
                .getSingleResult();
        return Optional.of(user);
    }

    @Override
    public void deleteUserAccount(UUID userId) {
        EntityManager entityManager = Database.getEntity();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, userId);
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }
}
