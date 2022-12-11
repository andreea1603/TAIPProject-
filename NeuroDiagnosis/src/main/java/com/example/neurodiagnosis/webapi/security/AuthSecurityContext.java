package com.example.neurodiagnosis.webapi.security;

import com.example.neurodiagnosis.webapi.filters.AuthorizationFilter;
import jakarta.ws.rs.core.SecurityContext;

import java.security.Principal;

public class AuthSecurityContext implements SecurityContext {

    private final UserPrincipal userPrincipal;

    public AuthSecurityContext(UserPrincipal userPrincipal) {

        this.userPrincipal = userPrincipal;
    }

    @Override
    public Principal getUserPrincipal() {
        return userPrincipal;
    }

    @Override
    public boolean isUserInRole(String s) {
        return true;
    }

    @Override
    public boolean isSecure() {
        return true;
    }

    @Override
    public String getAuthenticationScheme() {
        return AuthorizationFilter.AUTHENTICATION_SCHEME;
    }
}
