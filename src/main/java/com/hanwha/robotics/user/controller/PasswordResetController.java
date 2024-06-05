package com.hanwha.robotics.user.controller;

import com.hanwha.robotics.user.common.utils.MailUtil;
import com.hanwha.robotics.user.dto.ResetPasswordDto;
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

	/**
	 * 비밀번호 찾기
	 * @param request
	 * @return
	 */
	@PostMapping("/password/reset-page")
	@ResponseBody
	public ResponseEntity<ApiResponse<Void>> sendPasswordResetPage(@RequestBody MemberRequest request) {
		memberService.sendPasswordResetMail(request);
		return ResponseEntity.ok(ApiResponse.res(ApiStatus.OK.getValue(), ApiStatus.OK.name()));
	}

	/**
	 * 비밀번호 찾기 - 재설정 페이지
	 * @param token
	 * @return
	 */
	@GetMapping("/password/reset")
	public String resetPasswordPage(@RequestParam String token) {
		if (passwordResetTokenService.validate(token)) {
			return "/member/login_pwd_re";
		} else {
			return "/common/error";
		}
	}

	/**
	 * 비밀번호 재설정
	 * @param request
	 * @return
	 */
	@PutMapping("/password/reset")
	@ResponseBody
	public ResponseEntity<ApiResponse<Void>> resetPassword(@RequestBody ResetPasswordRequest request) {
		memberService.resetPassword(request);

		ResetPasswordDto dto = passwordResetTokenService.retrieveEmailAndRegion(request.getToken());

		mailUtil.sendPasswordChangeConfirm(dto.getEmail(), dto.getRegion());
		passwordResetTokenService.deleteToken(request.getToken());
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
