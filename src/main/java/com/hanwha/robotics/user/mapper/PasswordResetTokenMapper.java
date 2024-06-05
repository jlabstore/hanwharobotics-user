package com.hanwha.robotics.user.mapper;

import com.hanwha.robotics.user.dto.ResetPasswordDto;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.hanwha.robotics.user.entity.PasswordResetToken;

@Mapper
public interface PasswordResetTokenMapper {

  void insertToken(Map<String, Object> data);

  PasswordResetToken findByToken(String token);

  String findEmailByToken(String token);


  void deleteToken(String token);

  String findRegionByToken(String token);

  ResetPasswordDto findByEmailAndRegionByToken(String token);
}
