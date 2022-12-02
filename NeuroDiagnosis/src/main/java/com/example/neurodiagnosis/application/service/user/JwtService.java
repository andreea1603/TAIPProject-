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
    private static final String ISSUER = "javaApppUrl";
    private static final String AUDIENCE = "reactFeUrl";
    private static final Algorithm algorithm = Algorithm.HMAC256(SIGNING_KEY);


    public static String createJWT(User user) {

        return JWT.create()
                .withIssuer(ISSUER)
                .withAudience(AUDIENCE)
                .withJWTId(UUID.randomUUID().toString())
                .withClaim("userId", user.getId().toString())
                .withClaim("userEmail", user.getEmailAddress())
                .withClaim("userName", user.getUsername())
                .sign(algorithm);
    }

    public static Map<String, Claim> decodeJWT(String jwtTokenAsString) {
        DecodedJWT jwt = JWT.require(algorithm)
                .build()
                .verify(jwtTokenAsString);

        return jwt.getClaims();
    }
}