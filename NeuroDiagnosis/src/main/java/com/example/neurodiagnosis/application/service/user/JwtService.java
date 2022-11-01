package com.example.neurodiagnosis.application.service.user;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.RSAKeyProvider;
import com.example.neurodiagnosis.domain.entities.User;

import java.util.Map;
import java.util.UUID;

public class JwtService {
    private static final String SIGNING_KEY = "somehing16bytesplus";
    private static final Algorithm algorithm = Algorithm.HMAC256(SIGNING_KEY);


    public static String createJWT(User user, String issuer, String audience) {

        return JWT.create()
                .withIssuer(issuer)
                .withAudience(audience)
                .withJWTId(UUID.randomUUID().toString())
                .withClaim("userId", user.getId().toString())
                .withClaim("userEmail", user.getEmailAddress())
                .sign(algorithm);
    }

    public static Map<String, Claim> decodeJWT(String jwtTokenAsString) {
        DecodedJWT jwt = JWT.require(algorithm)
                .build()
                .verify(jwtTokenAsString);

        return jwt.getClaims();
    }
}