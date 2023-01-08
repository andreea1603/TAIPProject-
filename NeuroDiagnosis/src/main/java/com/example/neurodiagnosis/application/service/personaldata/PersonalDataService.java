package com.example.neurodiagnosis.application.service.personaldata;

import com.example.neurodiagnosis.application.interfaces.repositories.IMmseTestResultsRepository;
import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.domain.entities.TestResult;
import com.example.neurodiagnosis.domain.entities.User;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Named("PersonalDataService")
@NoArgsConstructor
@SessionScoped
public class PersonalDataService implements IPersonalDataService, Serializable {
    private IUserRepository userRepository;

    private IMmseTestResultsRepository mmseTestResultsRepository;

    @Inject
    public PersonalDataService(@Named("userRepository") IUserRepository userRepository, @Named("mmseTestResultsRepository") IMmseTestResultsRepository mmseTestResultsRepository) {
        this.userRepository = userRepository;
        this.mmseTestResultsRepository = mmseTestResultsRepository;
    }

    @Override
    public String getMlResult(UUID userId) throws IOException, InterruptedException {
        Optional<User> user = userRepository.findById(userId);
        List<TestResult> testResult = mmseTestResultsRepository.getTestResults(userId);
        if(user.isPresent()){
            User myUser = user.get();
            String url1 = "http://127.0.0.1:5000/text";
            var values = new HashMap<String, Float>(){{
                put("AGE", (float) myUser.getAge());
                put("PTGENDER", myUser.getGender()?1.0f:0.0f);
            }};
            switch (myUser.getMarriedStatus()) {
                case "Married":
                    values.put("PTMARRY", 0.0f);
                    break;
                case "Divorced":
                    values.put("PTMARRY", 1.0f);
                    break;
                case "Widowed":
                    values.put("PTMARRY", 2.0f);
                    break;
                case "Never married":
                    values.put("PTMARRY", 3.0f);
                    break;
            }
            switch (myUser.getEthnicity()) {
                case "White":
                    values.put("PTRACCAT", 0.0f);
                    break;
                case "Black":
                    values.put("PTRACCAT", 1.0f);
                    break;
                case "Asian":
                    values.put("PTRACCAT", 2.0f);
                    break;
                case "More than one":
                    values.put("PTRACCAT", 3.0f);
                    break;
                case "Other":
                    values.put("PTRACCAT", 4.0f);
                    break;
            }
            Integer score = testResult.get(testResult.size() -1).getTestResult();
            values.put("MMSE", score.floatValue());

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
