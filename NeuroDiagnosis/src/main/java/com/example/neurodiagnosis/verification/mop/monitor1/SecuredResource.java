package com.example.neurodiagnosis.verification.mop.monitor1;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.example.neurodiagnosis.application.service.user.JwtService;
import com.example.neurodiagnosis.webapi.security.UserPrincipal;

import java.util.Map;
import java.util.UUID;

public abstract class SecuredResource {

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



    private Map<String, Claim> validateToken(String token) throws Exception {
        Map<String, Claim> claims;

        try {
            claims = JwtService.decodeJWT(token);
        } catch (JWTVerificationException jwtVerificationException) {
            throw new Exception(jwtVerificationException);
        }

        return claims;
    }
}
