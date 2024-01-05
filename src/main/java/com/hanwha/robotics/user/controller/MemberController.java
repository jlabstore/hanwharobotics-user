package com.hanwha.robotics.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanwha.robotics.user.dto.MemberRequest;
import com.hanwha.robotics.user.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;

	/**
	 * 로그인 페이지
	 * @return
	 */
	@GetMapping("/login")
	public String loginPage() {
		return "contact/login";
	}

	/**
	 * 회원가입 페이지
	 * @return
	 */
	@GetMapping("/signup")
	public String signup() {
		return "contact/signup";
	}

	/**
	 * 회원가입
	 * @param request
	 * @return
	 */
	@PostMapping("/signup")
	public String register(MemberRequest request) {
		memberService.registerMember(request);
		return "main";
	}

	/**
	 * 회원Id 중복확인
	 * @param memberId
	 * @return
	 */
	@GetMapping("/check")
	@ResponseBody
	public boolean checkMemberId(@RequestParam String memberId) {
		return memberService.isMemberIdExists(memberId);
	}

	/**
	 * 아이디 찾기 페이지
	 * @return
	 */
	@GetMapping("/find-id")
	public String findIdPage() {
		return "find_id";
	}

	/**
	 * 비밀번호 찾기 페이지
	 * @return
	 */
	@GetMapping("/find-pw")
	public String findPwPage() {
		return "find_pw";
	}

	/**
	 * 아이디 찾기 - 이메일로 아이디 전송
	 * @return
	 */
	@PostMapping("/id/send")
	public ResponseEntity<Void> sendMemberId(@RequestBody MemberRequest request) {
		memberService.findId(request);
		return ResponseEntity.ok().build();
	}

	/**
	 * 비밀번호 찾기 - 임시비밀번호 전송
	 * @return
	 */
	@PutMapping("/temp-password/send")
	public ResponseEntity<Void> updatePassword(@RequestBody MemberRequest request) {
		memberService.findPassword(request);
		return ResponseEntity.ok().build();
	}
}
