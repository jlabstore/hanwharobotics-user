package com.hanwha.robotics.user.security;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationBusinessException extends AuthenticationException {
    public AuthenticationBusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthenticationBusinessException(String msg) {
        super(msg);
    }

}
