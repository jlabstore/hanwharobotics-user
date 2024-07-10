package com.hanwha.robotics.user.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class CommonUtil {


    /**
     * 다국어 쿠키값 반환 
     * @param request
     * @return
     */
    public String getCookieLang(HttpServletRequest request){
        String lang = getCookieValue("lang", request);
        return lang != null && !"".equals(lang) ? lang : null;
    }

    
    /**
     * 쿠키 값 반환
     * @param key : 쿠키 key 
     * @param request
     * @return
     */
    public String getCookieValue(String key, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null && key != null) {
            for (Cookie cookie : cookies) {
                if ((key).equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
  
}
