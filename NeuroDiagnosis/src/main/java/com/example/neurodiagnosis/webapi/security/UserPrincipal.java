package com.example.neurodiagnosis.webapi.security;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.Principal;
import java.util.UUID;


@Getter
@NoArgsConstructor
public class UserPrincipal implements Principal {

    public UUID userId;

    private String userName;

    private String email;

    public UserPrincipal(UUID userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;

        this.email = email;
    }

    @Override
    public String getName() {
        return userId.toString();
    }
}
