package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.common.utils.MailUtil;
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
	@Autowired
	private MailUtil mailUtil;

	@PostMapping("/password/reset-page")
	@ResponseBody
	public ResponseEntity<ApiResponse<Void>> sendPasswordResetPage(@RequestBody MemberRequest request) {
		memberService.sendPasswordResetMail(request);
		return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
	}

	@GetMapping("/password/reset")
	public String resetPasswordPage(@RequestParam String token) {
		if (passwordResetTokenService.validate(token)) {
			return "/member/login_pwd_re";
		} else {
			return "main"; // FIXME : 실패하면 어디로 보내야할까,,
		}
	}

	// FIXME: 재설정 후 메일 보내기
	@PutMapping("/password/reset")
	@ResponseBody
	public ResponseEntity<ApiResponse<Void>> resetPassword(@RequestBody ResetPasswordRequest request) {
		memberService.resetPassword(request);
		String email = passwordResetTokenService.retrieveEmail(request.getToken());
		mailUtil.sendPasswordChangeConfirm(email);
		// TODO: 비밀번호 재설정 완료되면 토큰 삭제
//		passwordResetTokenService.deleteToken(request.getToken());
		return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
	}

	/**
	 * 비밀번호 찾기 완료 페이지
	 * @return
	 */
	@GetMapping("/password/reset/complete")
	public String resetPwCompletePage() {
		return "member/login_pwd_re_complete";
	}

}
