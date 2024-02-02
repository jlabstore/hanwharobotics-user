package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.common.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.hanwha.robotics.user.dto.MemberRequest;
import com.hanwha.robotics.user.service.MemberService;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;

	/**
	 * 로그인 페이지
	 * @return
	 */
	@GetMapping("/login-page")
	public String loginPage() {
		return "member/login";
	}

	/**
	 * 회원가입 약관페이지
	 * @return
	 */
	@GetMapping("/signup/term")
	public String signupTerm() {
		return "member/signup_term";
	}

	/**
	 * 회원가입 페이지
	 * @return
	 */
	@GetMapping("/signup")
	public String signupPage() {
		return "member/signup";
	}

	//	@GetMapping("/signup")
//	public String signup(@RequestParam("agree") String agree) {
//		if("Y".equals(agree)) {
//			return "member/signup";
//		} else {
//			return "main";
//		}
//	}

	/**
	 * 회원가입
	 * @param request
	 * @return
	 */
	@PostMapping("/signup")
	public String signup(@ModelAttribute MemberRequest request) {
		memberService.registerMember(request);
		return "member/signup_complete";
	}

	/**
	 * 회원Id 중복확인 API
	 * @param memberId
	 * @return
	 */
	@GetMapping("/id/check")
	public ResponseEntity<Boolean> checkMemberId(@RequestParam String memberId) {
		boolean isExists = memberService.isMemberIdExists(memberId);
		return ResponseEntity.ok(isExists);
	}

	/**
	 * 아이디 찾기 페이지
	 * @return
	 */
	@GetMapping("/find-id")
	public String findIdPage() {
		return "member/find_id";
	}

	/**
	 * 아이디 찾기 - 이메일로 아이디 전송
	 * @return
	 */
	@PostMapping("/find-id")
	public ResponseEntity<Void> sendMemberId(@RequestBody MemberRequest request) {
		memberService.findId(request);
		return ResponseEntity.ok().build();
	}

	/**
	 * 아이디 찾기 완료 페이지
	 * @return
	 */
	@GetMapping("/find-id/complete")
	public String findIdCompletePage() {
		return "member/find_id_complete";
	}

	/**
	 * 비밀번호 찾기 페이지
	 * @return
	 */
	@GetMapping("/find-pw")
	public String resetPasswordPage() {
		return "member/find_pw";
	}






}
