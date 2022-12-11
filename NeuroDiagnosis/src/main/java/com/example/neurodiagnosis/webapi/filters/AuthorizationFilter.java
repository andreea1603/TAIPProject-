package com.example.neurodiagnosis.webapi.filters;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.example.neurodiagnosis.application.service.user.JwtService;
import com.example.neurodiagnosis.domain.exceptions.JwtValidationException;
import com.example.neurodiagnosis.webapi.annotations.EnforcesUserAuthorization;
import com.example.neurodiagnosis.webapi.security.AuthSecurityContext;
import com.example.neurodiagnosis.webapi.security.UserPrincipal;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@EnforcesUserAuthorization
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    private static final String REALM = "example";
    public static final String AUTHENTICATION_SCHEME = "Bearer";

    @Override
    public void filter(ContainerRequestContext requestContext) {

        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        String token = authorizationHeader
                .substring(AUTHENTICATION_SCHEME.length()).trim();

        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        var authorizedUser = validateAuthorization(token);

        if (authorizedUser.isPresent()) {
            acceptAuthorization(requestContext, authorizedUser.get());
        } else {
            abortWithUnauthorized(requestContext);
        }
    }


    public boolean isTokenBasedAuthentication(String authorizationHeader) {

        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    public Optional<UserPrincipal> validateAuthorization(String jwtToken) {

        try {

            // Validate the token
            var claims = validateToken(jwtToken);

            var userId = claims.get("userId")
                    .asString();

            var userEmail = claims.get("userEmail")
                    .asString();

            var userName = claims.get("userName")
                    .asString();

            return Optional.of(new UserPrincipal(UUID.fromString(userId),
                    userName, userEmail));

        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private void acceptAuthorization(ContainerRequestContext requestContext, UserPrincipal authorizedUser) {
        requestContext.setSecurityContext(new AuthSecurityContext(authorizedUser));
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                .build());
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
