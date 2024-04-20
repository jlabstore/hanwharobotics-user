//package com.hanwha.robotics.user.controller;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.view.RedirectView;
//
//@Controller
//@RequestMapping("/lang")
//public class LanguageController {
//
//	@Value("${base.url}")
//	private String baseUrl;
//
//	@Value("${base.url.en}")
//	private String baseUrlEn;
//
//	@Value("${cookie.url}")
//	private String cookieUrl;
//
//	@Value("${cookie.url.en}")
//	private String cookieUrlEn;
//
//	@GetMapping("/kr")
//	public RedirectView kr(
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
//
////		response.sendRedirect(baseUrl + path);
//		RedirectView redirectView = new RedirectView(baseUrl+path);
//		redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
//		return redirectView;
//	}
//
////	RedirectView
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
//
//
//
//
//
////	@GetMapping("/kr")
////	public ResponseEntity<Map<String ,String>> kr(
////			HttpServletRequest request,
////			HttpServletResponse response,
////			@RequestParam String path
////	) throws IOException {
////		Arrays.stream(request.getCookies())
////				.filter(cookie -> cookie.getName().equals("JSESSIONID"))
////				.findFirst()
////				.ifPresent(jSessionId -> {
////					Cookie cookie = new Cookie(jSessionId.getName(), jSessionId.getValue());
////
////					cookie.setValue(jSessionId.getValue());
////
////					cookie.setMaxAge(jSessionId.getMaxAge());
////					cookie.setSecure(jSessionId.getSecure());
////					cookie.setHttpOnly(jSessionId.isHttpOnly());
////					cookie.setVersion(jSessionId.getVersion());
////					cookie.setDomain(cookieUrl);
////					response.addCookie(cookie);
//////                    response.setHeader("Set-Cookie", "JSESSIONID=" + jSessionId.getValue() + "; SameSite=None; none");
////				});
//////		response.sendRedirect(baseUrl + path);
////        Map<String, String> responseMap = new HashMap<>();
////		responseMap.put("redirectUrl", baseUrl + path);
////		return ResponseEntity.ok(responseMap);
////	}
////
////	@GetMapping("/en")
////	public ResponseEntity<Map<String,String>> en(
////			HttpServletRequest request,
////			HttpServletResponse response,
////			@RequestParam String path
////	) throws IOException {
////		Arrays.stream(request.getCookies())
////				.filter(cookie -> cookie.getName().equals("JSESSIONID"))
////				.findFirst()
////				.ifPresent(jSessionId -> {
////					Cookie cookie = new Cookie(jSessionId.getName(), jSessionId.getValue());
////
////					cookie.setValue(jSessionId.getValue());
////
////					cookie.setMaxAge(jSessionId.getMaxAge());
////					cookie.setSecure(jSessionId.getSecure());
////					cookie.setHttpOnly(jSessionId.isHttpOnly());
////					cookie.setVersion(jSessionId.getVersion());
////					cookie.setDomain(cookieUrlEn);
////					response.addCookie(cookie);
////
//////					response.setHeader("Set-Cookie", "JSESSIONID=" + jSessionId.getValue() + "; SameSite=None; none");
////
////				});
//////		response.sendRedirect(baseUrlEn + path);
////        Map<String, String> responseMap = new HashMap<>();
////		responseMap.put("redirectUrl", baseUrlEn + path);
////		return ResponseEntity.ok(responseMap);
////	}
//
//
//}



package com.hanwha.robotics.user.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lang")
public class LanguageController {

	@Value("${base.url}")
	private String baseUrl;

	@Value("${base.url.en}")
	private String baseUrlEn;

	@Value("${cookie.url}")
	private String cookieUrl;

	@Value("${cookie.url.en}")
	private String cookieUrlEn;


	@GetMapping("/test")
	public void test(HttpServletResponse response) {
		ResponseCookie cookie = ResponseCookie.from("TEST", "1234")
				.path("/")
				.domain(cookieUrl)
				.secure(true)
				.httpOnly(false)
				.sameSite("None")
				.build();
		response.addHeader("Set-Cookie", cookie.toString());

		ResponseCookie cookie2 = ResponseCookie.from("TEST", "1234")
				.path("/")
				.domain(cookieUrlEn)
				.secure(true)
				.httpOnly(false)
				.sameSite("None")
				.build();
		response.addHeader("Set-Cookie", cookie2.toString());
	}


//	@GetMapping("/kr")
//	public void kr(
//			HttpServletRequest request,
//			HttpServletResponse response,
//			@RequestParam String path
//	) throws IOException {
//		Arrays.stream(request.getCookies())
//				.forEach(jSessionId -> {
//					Cookie cookie = new Cookie(jSessionId.getName(), jSessionId.getValue());
//					cookie.setMaxAge(jSessionId.getMaxAge());
//					cookie.setSecure(jSessionId.getSecure());
//					cookie.setHttpOnly(jSessionId.isHttpOnly());
//					cookie.setVersion(jSessionId.getVersion());
//					cookie.setDomain(cookieUrl);
//					cookie.setPath(cookieUrl);
//					response.addCookie(cookie);
//
//					// response.setHeader("Set-Cookie", "JSESSIONID=" + jSessionId.getValue() + "; SameSite=None; Secure");
//				});
//		System.out.println(baseUrl + path);
//		response.sendRedirect(baseUrl + path);
//		// response.sendRedirect(baseUrl + path);
//	}
//
////	RedirectView
//
//	@GetMapping("/en")
//	public void en(
//			HttpServletRequest request,
//			HttpServletResponse response,
//			@RequestParam String path
//	) throws IOException {
//		Arrays.stream(request.getCookies())
//				.forEach(jSessionId -> {
//					Cookie cookie = new Cookie(jSessionId.getName(), jSessionId.getValue());
//					cookie.setMaxAge(jSessionId.getMaxAge());
//					cookie.setSecure(jSessionId.getSecure());
//					cookie.setHttpOnly(jSessionId.isHttpOnly());
//					cookie.setVersion(jSessionId.getVersion());
//					cookie.setDomain(cookieUrlEn);
//					cookie.setPath(cookieUrl);
//					response.addCookie(cookie);
//
//					// response.setHeader("Set-Cookie", "JSESSIONID=" + jSessionId.getValue() + "; SameSite=None; Secure");
//
//				});
//		System.out.println(baseUrlEn + path);
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

					cookie.setValue(jSessionId.getValue());

					cookie.setMaxAge(jSessionId.getMaxAge());
					cookie.setSecure(jSessionId.getSecure());
					cookie.setHttpOnly(jSessionId.isHttpOnly());
					cookie.setVersion(jSessionId.getVersion());
					cookie.setDomain(cookieUrl);
					response.addCookie(cookie);
//                    response.setHeader("Set-Cookie", "JSESSIONID=" + jSessionId.getValue() + "; SameSite=None; none");
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

					cookie.setValue(jSessionId.getValue());

					cookie.setMaxAge(jSessionId.getMaxAge());
					cookie.setSecure(jSessionId.getSecure());
					cookie.setHttpOnly(jSessionId.isHttpOnly());
					cookie.setVersion(jSessionId.getVersion());
					cookie.setDomain(cookieUrlEn);
					response.addCookie(cookie);

//					response.setHeader("Set-Cookie", "JSESSIONID=" + jSessionId.getValue() + "; SameSite=None; none");

				});
//		response.sendRedirect(baseUrlEn + path);
        Map<String, String> responseMap = new HashMap<>();
		responseMap.put("redirectUrl", baseUrlEn + path);
		return ResponseEntity.ok(responseMap);
	}




}
