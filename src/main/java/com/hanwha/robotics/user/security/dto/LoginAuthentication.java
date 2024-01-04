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

    // 차라리 여기에 MemberRequest 를 받는 생성자를 만들자!

    @Override
    public String getCredentials() {
        return password;
    }

    @Override
    public String getPrincipal() {
        return memberId;
    }
}
