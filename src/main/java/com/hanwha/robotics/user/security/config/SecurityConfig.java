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
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
//@EnableRedisHttpSession
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
//                .addFilterBefore(new SessionHeaderFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic().disable()
                .formLogin().disable()
//                .cors().disable()
                .cors((cors -> cors.configurationSource(corsConfigurationSource())))
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


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(
                "https://hanwharobotics.co.kr",
                "https://hanwharobotics.com",
                "https://hanwharobotics.co.kr:8090",
                "https://hanwharobotics.com:8090"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

//    @Bean
//    public CookieSerializer cookieSerializer() {
//        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
//        serializer.setCookieName("JSESSIONID_2");
//        serializer.setCookiePath("/");
////        serializer.setDomainNamePattern("^.+?(\\w+\\.[a-z]+)$");
//        serializer.setDomainNamePattern("^.+?\\.hanwharobotics\\.(co\\.kr|com)$");
//        serializer.setUseBase64Encoding(false);
//        return serializer;
//    }



}
