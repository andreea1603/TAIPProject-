package com.example.neurodiagnosis.infrastructure.seed;

import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("usersFactory")
@SessionScoped
public class UsersFactory implements Serializable {


    private IUserRepository userRepository;

    @Inject
    public UsersFactory(IUserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void seedTestData() {
        if (this.userRepository.getUsersCount() > 0) {
            return;
        }

        //hash la "someGibberish"
        this.userRepository.createUser("User", "P.", "Nicu", "emailexistent1@gmail.com", "42876cc421d0212402f950f4fab255e329dd5e204439cbca2f0d8ed027d77822");
    }

    public void clearData() {
        this.userRepository.getEntityManager().getTransaction().begin();

        this.userRepository.getEntityManager().createNativeQuery("DELETE FROM users").executeUpdate();

        this.userRepository.getEntityManager().getTransaction().commit();
    }
}
