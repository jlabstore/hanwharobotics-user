package com.hanwha.robotics.user.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/lang")
//@CrossOrigin(origins = {"https://hanwharobotics.co.kr:8090", "https://hanwharobotics.com:8090", "https://hanwharobotics.co.kr", "https://hanwharobotics.com"} , methods = {RequestMethod.GET, RequestMethod.POST})
@CrossOrigin
public class LanguageController {

	@Value("${base.url}")
	private String baseUrl;

	@Value("${base.url.en}")
	private String baseUrlEn;

	@Value("${cookie.url}")
	private String cookieUrl;

	@Value("${cookie.url.en}")
	private String cookieUrlEn;

//	@GetMapping("/kr")
//	public void kr(
//		HttpServletRequest request,
//		HttpServletResponse response,
//		@RequestParam String path
//	) throws IOException {
//		Arrays.stream(request.getCookies())
//			.filter(cookie -> cookie.getName().equals("JSESSIONID"))
//			.findFirst()
//			.ifPresent(jSessionId -> {
//				Cookie cookie = new Cookie(jSessionId.getName(), jSessionId.getValue());
//				cookie.setMaxAge(jSessionId.getMaxAge());
//				cookie.setSecure(jSessionId.getSecure());
//				cookie.setHttpOnly(jSessionId.isHttpOnly());
//				cookie.setVersion(jSessionId.getVersion());
//				cookie.setDomain(cookieUrl);
//				response.addCookie(cookie);
//
//				response.setHeader("Set-Cookie", "JSESSIONID=" + jSessionId.getValue() + "; SameSite=None; Secure");
//			});
//		response.sendRedirect(baseUrl + path);
//	}
//
//
//
//	@GetMapping("/en")
//	public void en(
//		HttpServletRequest request,
//		HttpServletResponse response,
//		@RequestParam String path
//	) throws IOException {
//		Arrays.stream(request.getCookies())
//			.filter(cookie -> cookie.getName().equals("JSESSIONID"))
//			.findFirst()
//			.ifPresent(jSessionId -> {
//				Cookie cookie = new Cookie(jSessionId.getName(), jSessionId.getValue());
//				cookie.setMaxAge(jSessionId.getMaxAge());
//				cookie.setSecure(jSessionId.getSecure());
//				cookie.setHttpOnly(jSessionId.isHttpOnly());
//				cookie.setVersion(jSessionId.getVersion());
//				cookie.setDomain(cookieUrlEn);
//				response.addCookie(cookie);
//
//				response.setHeader("Set-Cookie", "JSESSIONID=" + jSessionId.getValue() + "; SameSite=None; Secure");
//
//			});
//		response.sendRedirect(baseUrlEn + path);
//	}





	@GetMapping("/kr")
	public ResponseEntity<Map<String ,String>> kr(
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
					cookie.setDomain(cookieUrl);
					response.addCookie(cookie);

					response.setHeader("Set-Cookie", "JSESSIONID=" + jSessionId.getValue() + "; SameSite=None; none");
				});
//		response.sendRedirect(baseUrl + path);
        Map<String, String> responseMap = new HashMap<>();
		responseMap.put("redirectUrl", baseUrl + path);
		return ResponseEntity.ok(responseMap);
	}

	@GetMapping("/en")
	public ResponseEntity<Map<String,String>> en(
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
					cookie.setDomain(cookieUrl);
					response.addCookie(cookie);

					response.setHeader("Set-Cookie", "JSESSIONID=" + jSessionId.getValue() + "; SameSite=None; none");
				});
//		response.sendRedirect(baseUrlEn + path);
        Map<String, String> responseMap = new HashMap<>();
		responseMap.put("redirectUrl", baseUrlEn + path);
		return ResponseEntity.ok(responseMap);
	}

}
