package com.hanwha.robotics.user.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.hanwha.robotics.user.entity.Member;

@Mapper
public interface MemberLogMapper {

	void insertMemberLog(HashMap<String, Object> data);
}
