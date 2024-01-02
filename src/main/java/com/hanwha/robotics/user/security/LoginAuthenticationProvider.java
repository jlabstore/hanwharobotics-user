package com.hanwha.robotics.user.security;

import com.hanwha.robotics.user.entity.Member;
import com.hanwha.robotics.user.security.dto.LoginAuthentication;
import com.hanwha.robotics.user.security.dto.LoginCompleteAuthentication;
import com.hanwha.robotics.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MemberService memberService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LoginAuthentication auth = (LoginAuthentication) authentication;
        String memberId = auth.getPrincipal();
        String password = auth.getCredentials();
        try {
            Member member = memberService.login(memberId, password);
            return new LoginCompleteAuthentication(member);
        } catch (RuntimeException ex) {
//            throw new AuthenticationBusinessException(ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return LoginAuthentication.class.isAssignableFrom(authentication);
    }
}
