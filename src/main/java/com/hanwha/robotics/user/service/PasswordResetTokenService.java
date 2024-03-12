package com.hanwha.robotics.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanwha.robotics.user.entity.PasswordResetToken;
import com.hanwha.robotics.user.mapper.PasswordResetTokenMapper;

public interface PasswordResetTokenService {

//	@Autowired
//	private PasswordResetTokenMapper passwordResetTokenMapper;
//
//	public boolean validate(String token) {
//		PasswordResetToken resetToken = passwordResetTokenMapper.findByToken(token);
//		return !resetToken.isExpired();
//	}

	/**
	 * 토큰 검증
	 * @param token
	 * @return
	 */
	boolean validate(String token);

	String retrieveEmail(String token);
}
