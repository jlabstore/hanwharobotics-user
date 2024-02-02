package com.hanwha.robotics.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanwha.robotics.user.entity.PasswordResetToken;
import com.hanwha.robotics.user.mapper.PasswordResetTokenMapper;

@Service
public class PasswordResetTokenService {

	@Autowired
	private PasswordResetTokenMapper passwordResetTokenMapper;

	public boolean validate(String token) {
		PasswordResetToken resetToken = passwordResetTokenMapper.findByToken(token);
		return !resetToken.isExpired();
	}
}
