package com.hanwha.robotics.user.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

@Component
public class LocaleUtils {
  public static Locale getLocaleFromCookie(HttpServletRequest httpRequest, String cookieName) {
    String lang = Optional.ofNullable(WebUtils.getCookie(httpRequest, cookieName))
        .map(Cookie::getValue)
        .orElse("ko");
    return "ko".equalsIgnoreCase(lang) ? Locale.KOREAN : Locale.ENGLISH;
  }
}

