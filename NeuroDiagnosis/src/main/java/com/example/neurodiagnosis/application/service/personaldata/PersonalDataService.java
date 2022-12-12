package com.example.neurodiagnosis.application.service.personaldata;

import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.webapi.dtos.PersonalDataMLDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Named("PersonalDataService")
@NoArgsConstructor
@SessionScoped
public class PersonalDataService implements IPersonalDataService, Serializable {
    private IUserRepository userRepository;

    @Inject
    public PersonalDataService(@Named("userRepository") IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getMlResult(UUID userId, PersonalDataMLDTO personalDataMLDTO) throws IOException, InterruptedException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User myUser = user.get();
            String url1 = "http://127.0.0.1:5000/text";
            var values = new HashMap<String, Float>(){{
                put("AGE", (float) myUser.getAge());
                put("PTGENDER", myUser.getGender()?1.0f:0.0f);
                put("PTEDUCAT", (float) personalDataMLDTO.educated);
                put("CDRSB", personalDataMLDTO.CDRSB);
                put("ADAS11", personalDataMLDTO.ADAS11);
                put("ADAS13", personalDataMLDTO.ADAS13);
                put("ADASQ4", personalDataMLDTO.ADASQ4);
                put("MMSE", personalDataMLDTO.MMSE);
                put("RAVLT_immediate", personalDataMLDTO.RAVLT_immediate);
                put("RAVLT_learning", personalDataMLDTO.RAVLT_learning);
                put("RAVLT_forgetting", personalDataMLDTO.RAVLT_forgetting);
                put("RAVLT_perc_forgetting", personalDataMLDTO.RAVLT_perc_forgetting);
                put("FAQ", personalDataMLDTO.FAQ);
                put("mPACCdigit", personalDataMLDTO.mPACCdigit);
                put("mPACCtrailsB", personalDataMLDTO.mPACCtrailsB);
                put("CDRSB_bl", personalDataMLDTO.CDRSB_bl);
                put("ADAS11_bl", personalDataMLDTO.ADAS11_bl);
                put("ADAS13_bl", personalDataMLDTO.ADAS13_bl);
                put("ADASQ4_bl", personalDataMLDTO.ADASQ4_bl);
                put("MMSE_bl", personalDataMLDTO.MMSE_bl);
                put("RAVLT_immediate_bl", personalDataMLDTO.RAVLT_immediate_bl);
                put("RAVLT_learning_bl", personalDataMLDTO.RAVLT_learning_bl);
                put("RAVLT_forgetting_bl", personalDataMLDTO.RAVLT_forgetting_bl);
                put("RAVLT_perc_forgetting_bl", personalDataMLDTO.RAVLT_perc_forgetting_bl);
                put("FAQ_bl", personalDataMLDTO.FAQ_bl);
                put("mPACCdigit_bl", personalDataMLDTO.mPACCdigit_bl);
                put("mPACCtrailsB_bl", personalDataMLDTO.mPACCtrailsB_bl);
            }};

            var objectMapper = new ObjectMapper();
            String requestBody = objectMapper
                    .writeValueAsString(values);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url1))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            return  response.body();
        }
        return null;
    }
}
