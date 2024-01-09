package com.hanwha.robotics.user.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanwha.robotics.user.security.dto.LoginCompleteAuthentication;
import com.hanwha.robotics.user.security.dto.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;

    public LoginAuthenticationFilter(String defaultFilterProcessesUrl, ObjectMapper objectMapper) {
        super(defaultFilterProcessesUrl);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        try {
            LoginRequest loginReq = new ObjectMapper().readValue(request.getReader(), LoginRequest.class);
            return super.getAuthenticationManager().authenticate(loginReq.toAuthentication());
        } catch (RuntimeException ex) {
            throw new AuthenticationBusinessException("로그인에 실패하였습니다.", ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        LoginCompleteAuthentication auth = (LoginCompleteAuthentication) authResult;
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.clearContext();
        SecurityContextHolder.setContext(context);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("로그인 실패 ", failed);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        if (failed instanceof AuthenticationBusinessException) {
            AuthenticationBusinessException ex = (AuthenticationBusinessException) failed;
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(ex.getCause()));
        }
    }
}
