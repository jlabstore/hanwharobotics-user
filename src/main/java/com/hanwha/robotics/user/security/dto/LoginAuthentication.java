package com.hanwha.robotics.user.security.dto;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.Collections;

public class LoginAuthentication extends AbstractAuthenticationToken {

    private final String memberId;
    private final String password;

    public LoginAuthentication(String userId, String password) {
        super(Collections.emptyList());
        this.memberId = userId;
        this.password = password;
    }

    @Override
    public String getCredentials() {
        return password;
    }

    @Override
    public String getPrincipal() {
        return memberId;
    }
}
