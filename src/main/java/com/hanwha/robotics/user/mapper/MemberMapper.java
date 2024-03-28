package com.hanwha.robotics.user.mapper;

import com.hanwha.robotics.user.dto.MemberResponse;
import com.hanwha.robotics.user.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    void insertMember(Member member);

	int checkMemberId(String memberId);

	int checkMemberEmail(String email);

	void updatePassword(String memberId, String password);

	Member selectByMemberId(String memberId);

    Member selectByMemberNo(int memberNo);

	Member selectByEmail(String email);

    void deleteMember(int memberNo);

    MemberResponse findByMemberNo(int memberNo);

	void updateMember(Member member);

	String findEmailByMemberNo(int memberNo);

}
