package com.hanwha.robotics.user.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

public class LangFilter extends OncePerRequestFilter {

  @Value("${base.url}")
  private String baseUrl;

  @Value("${base.url.en}")
  private String baseUrlEn;

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

    Cookie langCookie = new Cookie("lang", lang);
    langCookie.setPath("/");
//    langCookie.setMaxAge(60 * 60 * 24 * 30);
    langCookie.setSecure(true);
    langCookie.setHttpOnly(false);
    response.addCookie(langCookie);

    filterChain.doFilter(request, response);
  }

}
