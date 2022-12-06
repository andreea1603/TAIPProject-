package com.example.neurodiagnosis.application.service.personaldata;

import com.example.neurodiagnosis.application.interfaces.repositories.IQuestionRepository;
import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.webapi.dtos.PersonalDataDTO;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.UUID;

@Named("PersonalDataService")
public class PersonalDataService implements IPersonalDataService{
    private IUserRepository userRepository;

    @Inject
    public PersonalDataService(@Named("userRepository") IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User updateData(UUID userId, PersonalDataDTO personalDataDTO) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            User myUser = user.get();
            EntityManager em = userRepository.getEntityManager();
            em.getTransaction().begin();

            if(personalDataDTO.age > 0) {
                myUser.setAge(personalDataDTO.age);
            }
            myUser.setGender(personalDataDTO.gender);
            if(personalDataDTO.country != null) {
                myUser.setCountry(personalDataDTO.country);
            }
            if(personalDataDTO.city != null) {
                myUser.setCity(personalDataDTO.city);
            }
            if(personalDataDTO.province != null) {
                myUser.setProvince(personalDataDTO.province);
            }
            myUser.setHandedness(personalDataDTO.handedness);
            if(personalDataDTO.marriedStatus != null) {
                myUser.setMarriedStatus(personalDataDTO.marriedStatus);
            }

            em.persist(myUser);
            em.getTransaction().commit();
            return myUser;
        }

        return null;
    }
}
