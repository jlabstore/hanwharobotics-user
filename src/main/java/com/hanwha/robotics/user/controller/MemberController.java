package com.hanwha.robotics.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
	 * @param agree
	 * @return
	 */
	@GetMapping("/signup")
	public String signup(@RequestParam("agree") String agree) {
		if("Y".equals(agree)) {
			return "member/signup";
		} else {
			return "main";
		}
	}


	/**
	 * 회원가입 - 등록
	 * @param request
	 * @return
	 */
	@PostMapping("/signup")
	public String register(@ModelAttribute MemberRequest request) {
		memberService.registerMember(request);
		return "member/signup_complete";
	}

	/**
	 * 회원Id 중복확인
	 * @param memberId
	 * @return
	 */
	@GetMapping("/check")
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
	@PostMapping("/id/send")
	public ResponseEntity<Void> sendMemberId(@RequestBody MemberRequest request) {
		memberService.findId(request);
		return ResponseEntity.ok().build();
	}


	/**
	 * 비밀번호 찾기 페이지
	 * @return
	 */
	@GetMapping("/find-pw")
	public String findPwPage() {
		return "member/find_pw";
	}

	// TODO: 비밀번호 재설정 링크 전송으로 변경
	/**
	 * 비밀번호 찾기 - 임시비밀번호 전송
	 * @return
	 */
	@PutMapping("/temp-password/send")
	public ResponseEntity<Void> updatePassword(@RequestBody MemberRequest request) {
		memberService.findPassword(request);
		return ResponseEntity.ok().build();
	}

	/**
	 * 비밀번호 재설정 페이지
	 * @return
	 */
	@GetMapping("/reset/password")
	public String resetPwPage() {
		return "member/reset_pw";
	}


//	@PutMapping("/reset/password")
//	public ResponseEntity<Void> resetPassword(
//			@AuthenticationPrincipal String memberId,
//			@RequestBody MemberRequest request
//	) {
//		memberService.resetPassword(memberId, request);
//		return ResponseEntity.ok().build();
//	}

	/**
	 * 비밀번호 재설정
	 * @param memberNo
	 * @param request
	 * @return
	 */
	@PutMapping("/reset/password")
	public ResponseEntity<Void> resetPassword(
			@AuthenticationPrincipal int memberNo,
			@RequestBody MemberRequest request
	) {
		memberService.resetPassword(memberNo, request);
		return ResponseEntity.ok().build();
	}

	



}
