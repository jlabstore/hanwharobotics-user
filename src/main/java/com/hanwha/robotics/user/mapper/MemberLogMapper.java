package com.hanwha.robotics.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hanwha.robotics.user.entity.Member;

@Mapper
public interface MemberLogMapper {

	void insertMemberLog(Member member);
}
