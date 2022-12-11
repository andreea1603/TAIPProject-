package com.example.neurodiagnosis.verification.mop.monitor1;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.example.neurodiagnosis.application.service.database.DatabaseContextLive;
import com.example.neurodiagnosis.application.service.user.JwtService;
import com.example.neurodiagnosis.domain.entities.TestResult;
import com.example.neurodiagnosis.domain.exceptions.JwtValidationException;
import com.example.neurodiagnosis.infrastructure.repositories.MmseTestResultsRepository;
import com.example.neurodiagnosis.webapi.security.UserPrincipal;

import java.rmi.AccessException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TestResultsResource extends SecuredResource {



    public List<TestResult> getTestResults () throws AccessException {

        if (!successfullyAuthenticated) {
            throw new AccessException("Not authenticated");
        }

        var userId = user.getName();


        var testResultsRepo = new MmseTestResultsRepository(new DatabaseContextLive());

        return testResultsRepo.getTestResults(UUID.fromString(userId));
    }

    public boolean successfullyAuthenticated = false;
    public UserPrincipal user = null;

    public boolean authorize(String jwtToken) {
        Map<String, Claim> claims;

        try {
            claims = validateToken(jwtToken);
        } catch (Exception e) {
            successfullyAuthenticated = false;
            user = null;

            return false;
        }

        var userId = claims.get("userId").asString();
        var userEmail = claims.get("userEmail").asString();
        var userName = claims.get("userName").asString();

        user = new UserPrincipal(UUID.fromString(userId), userName, userEmail);
        successfullyAuthenticated = true;

        return true;
    }



    private Map<String, Claim> validateToken(String token) throws JwtValidationException {
        Map<String, Claim> claims;

        try {
            claims = JwtService.decodeJWT(token);
        } catch (JWTVerificationException jwtVerificationException) {
            throw new JwtValidationException(jwtVerificationException);
        }

        return claims;
    }


}
