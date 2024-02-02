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

import com.hanwha.robotics.user.dto.MemberRequest;
import com.hanwha.robotics.user.service.MemberService;
import com.hanwha.robotics.user.service.PasswordResetTokenService;

@Controller
public class PasswordResetController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private PasswordResetTokenService passwordResetTokenService;

	@PostMapping("/password/reset-page")
	public String sendPasswordResetPage(@RequestBody MemberRequest request) {
		memberService.sendPasswordResetMail(request);
		return "main";
	}

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
	public ResponseEntity<Void> resetPassword(
		@AuthenticationPrincipal int memberNo,
		@RequestBody MemberRequest request
	) {
		memberService.resetPassword(memberNo, request);
		return ResponseEntity.ok().build();
	}

}
