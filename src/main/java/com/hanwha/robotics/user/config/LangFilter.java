package com.hanwha.robotics.user.config;

import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

public class LangFilter extends OncePerRequestFilter {

  private final String baseUrl;
  private final String baseUrlEn;

  public LangFilter(String baseUrl, String baseUrlEn) {
    this.baseUrl = baseUrl;
    this.baseUrlEn = baseUrlEn;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String serverName = request.getServerName();
    String expectedLang = "ko";

    if (serverName.endsWith(baseUrlEn)) {
      expectedLang = "en";
    }

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      Optional<Cookie> langCookieOpt = Arrays.stream(cookies)
          .filter(cookie -> "lang".equals(cookie.getName()))
          .findFirst();

      if (langCookieOpt.isPresent()) {
        Cookie langCookie = langCookieOpt.get();
        if (!expectedLang.equals(langCookie.getValue())) {
          langCookie.setValue(expectedLang);
          response.addCookie(langCookie);
        }
        filterChain.doFilter(request, response);
        return;
      }
    }

    Cookie langCookie = new Cookie("lang", expectedLang);
//    langCookie.setPath("/");
//    langCookie.setMaxAge(60 * 60 * 24 * 30);
//    langCookie.setSecure(true);
//    langCookie.setHttpOnly(false);
    response.addCookie(langCookie);

    filterChain.doFilter(request, response);
  }



}




