//package com.hanwha.robotics.user.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterConfig {
//
//  @Value("${cookie.url}")
//  private String baseUrl;
//
//  @Value("${cookie.url.en}")
//  private String baseUrlEn;
//
//  @Bean
//  public FilterRegistrationBean<LangFilter> langCookieFilter() {
//    FilterRegistrationBean<LangFilter> registrationBean = new FilterRegistrationBean<>();
//    registrationBean.setFilter(new LangFilter(baseUrl, baseUrlEn));
//    registrationBean.addUrlPatterns("/*");
//    return registrationBean;
//  }
//}
