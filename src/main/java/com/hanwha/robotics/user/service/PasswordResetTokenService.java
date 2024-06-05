package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.dto.ResetPasswordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanwha.robotics.user.entity.PasswordResetToken;
import com.hanwha.robotics.user.mapper.PasswordResetTokenMapper;

public interface PasswordResetTokenService {

	/**
	 * 토큰 검증
	 * @param token
	 * @return
	 */
	boolean validate(String token);

	/**
	 * 메일 조회
	 * @param token
	 * @return
	 */
	String retrieveEmail(String token);

//	String retrieveEmailAndRegion(String token);

	/**
	 * 토큰 삭제
	 * @param token
	 */
    void deleteToken(String token);

	String retrieveRegion(String email);

	ResetPasswordDto retrieveEmailAndRegion(String token);
}
