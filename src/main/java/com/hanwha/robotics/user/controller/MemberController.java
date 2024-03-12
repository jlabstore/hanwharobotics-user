package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.hanwha.robotics.user.dto.MemberRequest;
import com.hanwha.robotics.user.service.MemberService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private CodeService codeService;

	/**
	 * 로그인 페이지
	 * @return
	 */
	@GetMapping("/login-page")
	public String loginPage() {
		return "member/login_main";
	}

	/**
	 * 회원가입 약관페이지
	 * @return
	 */
	@GetMapping("/signup/term")
	public String signupTerm() {
		return "member/signup_terms";
	}

	/**
	 * 회원가입 페이지
	 * @return
	 */
	@GetMapping("/signup")
	public String signupPage(Model model) {
		List<Map<String, Object>> countries = codeService.getAllCountries(); // FIXME
		List<Map<String, Object>> phoneCodes = codeService.getPhoneCodes();
		model.addAttribute("countries", countries);
		model.addAttribute("phoneCodes",phoneCodes);
		return "member/signup_main";
	}

	/**
	 * 회원가입 등록
	 * @param request
	 * @return
	 */
//	@PostMapping("/signup")
//	public String signup(@ModelAttribute MemberRequest request) {
//		memberService.registerMember(request);
//		return "member/signup_complete";
//	}

	/**
	 * 회원가입 등록
	 * @param request
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<Object> signup(@RequestBody MemberRequest request) {
		memberService.registerMember(request);
		return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
	}

	@GetMapping("/signup/complete")
	public String signupCompletePage() {
		return "member/signup_complete";
	}

	/**
	 * Id 중복확인
	 * @param memberId
	 * @return
	 */
	@GetMapping("/check/id")
	public ResponseEntity<Boolean> checkMemberId(@RequestParam String memberId) {
		boolean isExists = memberService.isMemberIdExists(memberId);
		return ResponseEntity.ok(isExists);
	}

	/**
	 * 이메일 중복확인
	 * @param email
	 * @return
	 */
	@GetMapping("/check/email")
	public ResponseEntity<Boolean> checkMemberEmail(@RequestParam String email) {
		System.out.println("이메일"+ email);
		boolean isExists = memberService.isMemberEmailExists(email);
		return ResponseEntity.ok(isExists);
	}

	/**
	 * 아이디 찾기 페이지
	 * @return
	 */
	@GetMapping("/find-id")
	public String findIdPage() {
		return "member/login_find_id";
	}

	/**
	 * 아이디 찾기 - 이메일로 아이디 전송
	 * @return
	 */
	@PostMapping("/find-id")
	public ResponseEntity<Object> sendMemberId(@Valid @RequestBody MemberRequest request) {
		memberService.findId(request);
		return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
	}

	/**
	 * 아이디 찾기 완료 페이지
	 * @return
	 */
	@GetMapping("/find-id/complete")
	public String findIdCompletePage() {
		return "member/login_find_id_complete";
	}

	/**
	 * 비밀번호 찾기 페이지
	 * @return
	 */
	@GetMapping("/find-pw")
	public String resetPwPage() {
		return "member/login_find_pwd";
	}

	/**
	 * 비밀번호 찾기 페이지
	 * @return
	 */
	@GetMapping("/find-pw/complete")
	public String resetPwCompletePage() {
		return "member/login_find_pwd_complete";
	}


	@GetMapping("/delete/complete")
	public String deleteAccountCompletePage() {
		return "/member/mypage_withdrawal_complete";
	}






}
