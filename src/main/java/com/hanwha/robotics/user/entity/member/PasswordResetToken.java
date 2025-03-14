package com.hanwha.robotics.user.entity;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasswordResetToken {

	private int tokenNo;
	private int memberNo;
	private String token;
	private LocalDateTime expiredDt;
	private LocalDateTime createDt;
	private String createBy;

	public boolean isExpired() {
		return this.expiredDt.isBefore(LocalDateTime.now());
	}
}
