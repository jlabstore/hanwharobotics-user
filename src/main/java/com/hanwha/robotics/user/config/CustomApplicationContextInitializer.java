package com.hanwha.robotics.user.config;

import java.util.Locale;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

public class CustomApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    ConfigurableWebApplicationContext context = (ConfigurableWebApplicationContext) applicationContext;
    ServletContext servletContext = context.getServletContext();
    LocaleResolver localeResolver = context.getBean(LocaleResolver.class);

    // 쿠키에서 언어 설정을 읽어서 LocaleResolver에 적용
    String langFromCookie = readLanguageFromCookie(Objects.requireNonNull(servletContext));
    if (langFromCookie != null) {
      setLocaleFromCookie(langFromCookie, localeResolver);
    }
  }

  private void setLocaleFromCookie(String langFromCookie, LocaleResolver localeResolver) {
    if ("en".equals(langFromCookie)) {
      ((CookieLocaleResolver) localeResolver).setDefaultLocale(Locale.ENGLISH);
    } else if ("ko".equals(langFromCookie)) {
      ((CookieLocaleResolver) localeResolver).setDefaultLocale(Locale.KOREAN);
    }
  }

  private String readLanguageFromCookie(ServletContext servletContext) {
    HttpServletRequest request = (HttpServletRequest) servletContext.getAttribute("javax.servlet.http.HttpServletRequest");
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if ("lang".equals(cookie.getName())) {
          return cookie.getValue(); // 쿠키에서 언어 값 반환
        }
      }
    }
    return null; // 쿠키에서 lang 쿠키가 없을 경우 null 반환
  }
}