package com.hanwha.robotics.user.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/lang")
public class LanguageController {

	@Value("${base.url}")
	private String baseUrl;

	@Value("${base.url.en}")
	private String baseUrlEn;

	@GetMapping("/kr")
	public void kr(
		HttpServletRequest request,
		HttpServletResponse response,
		@RequestParam String path
	) throws IOException {
		Arrays.stream(request.getCookies())
			.filter(cookie -> cookie.getName().equals("JSESSIONID"))
			.findFirst()
			.ifPresent(jSessionId -> {
				Cookie cookie = new Cookie(jSessionId.getName(), jSessionId.getValue());
				cookie.setMaxAge(jSessionId.getMaxAge());
				cookie.setSecure(jSessionId.getSecure());
				cookie.setHttpOnly(jSessionId.isHttpOnly());
				cookie.setVersion(jSessionId.getVersion());
//				cookie.setDomain("https://hanwharobotics.co.kr");
				cookie.setDomain(baseUrl);
				response.addCookie(cookie);
			});
//		response.sendRedirect("https://hanwharobotics.co.kr" + path);
		response.sendRedirect(baseUrl + path);
//		response.sendRedirect("http://localhost:8081" + path);

	}

	@GetMapping("/en")
	public void en(
		HttpServletRequest request,
		HttpServletResponse response,
		@RequestParam String path
	) throws IOException {
		Arrays.stream(request.getCookies())
			.filter(cookie -> cookie.getName().equals("JSESSIONID"))
			.findFirst()
			.ifPresent(jSessionId -> {
				Cookie cookie = new Cookie(jSessionId.getName(), jSessionId.getValue());
				cookie.setMaxAge(jSessionId.getMaxAge());
				cookie.setSecure(jSessionId.getSecure());
				cookie.setHttpOnly(jSessionId.isHttpOnly());
				cookie.setVersion(jSessionId.getVersion());
//				cookie.setDomain("https://hanwharobotics.com");
				cookie.setDomain(baseUrlEn);
				response.addCookie(cookie);
			});
//		response.sendRedirect("https://hanwharobotics.com" + path);
		response.sendRedirect(baseUrlEn + path);
//		response.sendRedirect("http://localhost:8081" + path);

	}

}
