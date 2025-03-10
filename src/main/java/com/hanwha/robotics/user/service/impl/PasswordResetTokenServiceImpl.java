package com.hanwha.robotics.user.service.impl;

import com.hanwha.robotics.user.dto.ResetPasswordDto;
import com.hanwha.robotics.user.entity.PasswordResetToken;
import com.hanwha.robotics.user.mapper.PasswordResetTokenMapper;
import com.hanwha.robotics.user.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

  @Autowired
  private PasswordResetTokenMapper passwordResetTokenMapper;

  @Override
  public boolean validate(String token) {
    PasswordResetToken resetToken = passwordResetTokenMapper.findByToken(token);
    if (resetToken == null) {
      return false;
    }
    return !resetToken.isExpired();
  }

  @Override
  public String retrieveEmail(String token) {
    return passwordResetTokenMapper.findEmailByToken(token);
  }

  @Override
  public void deleteToken(String token) {
    passwordResetTokenMapper.deleteToken(token);
  }

  @Override
  public ResetPasswordDto retrieveEmailAndRegion(String token) {
    return passwordResetTokenMapper.findByEmailAndRegionByToken(token);
  }

}
