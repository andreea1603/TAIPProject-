package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.application.service.database.IDatabaseContext;
import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.infrastructure.repositories.base.BaseRepository;
import com.example.neurodiagnosis.webapi.dtos.RegisterRequestDTO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Named("userRepository")
@SessionScoped
public class UserRepository extends BaseRepository
        implements IUserRepository, Serializable {

    @Inject
    public UserRepository(@Named("DatabaseContextLive") IDatabaseContext databaseContext) {
        super(databaseContext);
    }

    @Override
    public User createUser(String username, String lastName, String firstName, String email, String passwordHash) {
        User user = new User();

        user.setUsername(username);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setPasswordHash(passwordHash);
        user.setEmailAddress(email);
        user.setId(UUID.randomUUID());

        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
        em.persist(user);
        em.getTransaction().commit();

        return user;
    }

    @Override
    public User createUser(RegisterRequestDTO registerRequestDTO, String passwordHash) {
        User user = new User();

        user.setUsername(registerRequestDTO.getUsername());
        user.setLastName(registerRequestDTO.getLastName());
        user.setFirstName(registerRequestDTO.getFirstName());
        user.setPasswordHash(passwordHash);
        user.setEmailAddress(registerRequestDTO.getEmailAddress());
        user.setId(UUID.randomUUID());
        user.setAge(registerRequestDTO.getAge());
        user.setGender(registerRequestDTO.getGender());
        user.setCountry(registerRequestDTO.getCountry());
        user.setProvince(registerRequestDTO.getProvince());
        user.setCity(registerRequestDTO.getCity());
        user.setHandedness(registerRequestDTO.getHandedness());
        user.setMarriedStatus(registerRequestDTO.getMarriedStatus());

        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
        em.persist(user);
        em.getTransaction().commit();

        return user;
    }

    @Override
    public User updateUserPassword(UUID userId, String passwordHash) {
//        EntityManager entityManager = Database.getEntity();
        return null;
    }

    public Optional<User> findById(UUID id) {
        try {
            return Optional.of(em.find(User.class, id));
        } catch (Exception exception) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {

        List<User> users = em.createNamedQuery("User.findByEmail", User.class)
                .setParameter("email", email)
                .getResultList();

        if (users.size() == 0) {
            return Optional.empty();
        }

        return Optional.of(users.get(0));
    }

    @Override
    public Optional<User> findByUsername(String userName) {
        List<User> users = em.createNamedQuery("User.findByUsername", User.class)
                .setParameter("username", userName)
                .getResultList();

        if (users.size() == 0) {
            return Optional.empty();
        }

        return Optional.of(users.get(0));
    }
    @Override
    public void deleteUserAccount(UUID userId) {
        em.getTransaction().begin();

        User user = em.find(User.class, userId);
        em.remove(user);

        em.getTransaction().commit();
    }

    @Override
    public int getUsersCount() {
        //TODO: Optimizat sa nu incarce totul din DB
        var result = em.createNamedQuery("User.countUsers", User.class);

        var users = em.createNamedQuery("User.countUsers").getResultList();

        return users.size();
    }
}
