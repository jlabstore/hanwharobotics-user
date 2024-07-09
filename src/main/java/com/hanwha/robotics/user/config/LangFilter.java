package com.hanwha.robotics.user.config;

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
    String lang = "kr";

    if (serverName.endsWith(baseUrlEn)) {
      lang = "en";
    } else if (serverName.endsWith(baseUrl)) {
      lang = "kr";
    }

    boolean langCookieExists = Arrays.stream(request.getCookies())
        .anyMatch(cookie -> cookie.getName().equals("lang"));

    if (!langCookieExists) {
      Cookie langCookie = new Cookie("lang", lang);
      langCookie.setPath("/");
      langCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
      langCookie.setSecure(true);
      langCookie.setHttpOnly(false);
      response.addCookie(langCookie);
    }

    filterChain.doFilter(request, response);
  }
}
