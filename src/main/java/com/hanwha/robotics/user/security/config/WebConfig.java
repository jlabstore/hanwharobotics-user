package com.hanwha.robotics.user.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://hanwharobotics.co.kr",
                        "https://hanwharobotics.com",
                        "https://hanwharobotics.co.kr:8090",
                        "https://hanwharobotics.com:8090"
                )
                .allowedMethods("GET", "POST")
                .allowCredentials(true)
                .allowedHeaders("*");
    }
}
