package com.hanwha.robotics.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanwha.robotics.user.common.dto.ApiResponse;
import com.hanwha.robotics.user.common.enums.ApiStatus;
import com.hanwha.robotics.user.dto.MemberRequest;
import com.hanwha.robotics.user.dto.ResetPasswordRequest;
import com.hanwha.robotics.user.service.MemberService;
import com.hanwha.robotics.user.service.PasswordResetTokenService;

@Controller
public class PasswordResetController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private PasswordResetTokenService passwordResetTokenService;

	@PostMapping("/password/reset-page")
	@ResponseBody
	public ResponseEntity<ApiResponse<Void>> sendPasswordResetPage(@RequestBody MemberRequest request) {
		memberService.sendPasswordResetMail(request);
		return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
	}

	/**
	 *  Reset Token 에 MemberId 를 넣기
	 *   1. 이상한 사람 인지 파악하기 위함. (Auditing)
	 */
	@GetMapping("/password/reset")
	public String resetPasswordPage(
		@RequestParam String token
	) {
		if (passwordResetTokenService.validate(token)) {
			return "/member/reset_pw";
		} else {
			return "main"; // FIXME : 실패하면 어디로 보내야할까,,
		}
	}

	@PutMapping("/password/reset")
	@ResponseBody
	public ResponseEntity<ApiResponse<Void>> resetPassword(
		@RequestBody ResetPasswordRequest request
	) {
		memberService.resetPassword(request);
		return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
	}

	@GetMapping("/password/reset/complete")
	public String resetPwCompletePage() {
		return "/member/reset_pw_complete";
	}

}
