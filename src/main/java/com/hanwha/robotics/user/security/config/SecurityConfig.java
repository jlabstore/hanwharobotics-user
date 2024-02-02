package com.hanwha.robotics.user.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanwha.robotics.user.security.LoginAuthenticationFilter;
import com.hanwha.robotics.user.security.LoginAuthenticationProvider;
import com.hanwha.robotics.user.security.LogoutAuthenticationFilter;
import com.hanwha.robotics.user.service.impl.MemberLogService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final ObjectMapper objectMapper;
    private final LoginAuthenticationProvider loginAuthenticationProvider;
    private final MemberLogService memberLogService;

    @Bean
    public AuthenticationManager configureAuthenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(loginAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }

    public LoginAuthenticationFilter loginAuthenticationFilter(AuthenticationManager authenticationManager) throws Exception {
        LoginAuthenticationFilter loginFilter = new LoginAuthenticationFilter("/login", objectMapper);
        loginFilter.setAuthenticationManager(authenticationManager);
        return loginFilter;
    }

    public LogoutAuthenticationFilter logoutAuthenticationFilter() {
        return new LogoutAuthenticationFilter(memberLogService);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http
                .httpBasic().disable()
                .formLogin().disable()
                .cors().disable()
                .csrf().disable()
                .headers().frameOptions().disable()

                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/member/**").authenticated()
                .antMatchers("/qna/**").authenticated()

                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    response.sendRedirect("/login-page");
                })

                .and()
                .addFilterBefore(loginAuthenticationFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(logoutAuthenticationFilter(), LogoutFilter.class)



        ;
        return http.build();
    }
}
