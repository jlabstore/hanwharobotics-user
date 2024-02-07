package com.hanwha.robotics.user.service.impl;

import com.hanwha.robotics.user.entity.PasswordResetToken;
import com.hanwha.robotics.user.mapper.PasswordResetTokenMapper;
import com.hanwha.robotics.user.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenMapper passwordResetTokenMapper;

    public boolean validate(String token) {
        PasswordResetToken resetToken = passwordResetTokenMapper.findByToken(token);
        return !resetToken.isExpired();
    }

}
