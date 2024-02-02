package com.hanwha.robotics.user.mapper;

import java.util.Optional;

import com.hanwha.robotics.user.dto.MemberResponse;
import com.hanwha.robotics.user.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void insertMember(Member member);
	void updatePassword(String memberId, String password);

	int checkMemberId(String memberId);

	//
	Member selectByMemberId(String memberId);

    Member selectByMemberNo(int memberNo);


    Member selectByMemberIdAndEmail(String memberId, String email);

	Member selectByMemberId(String memberId, String email);

    Member selectByNameAndEmail(String name, String email);

	// 아이디 찾기
	Member selectByEmail(String email);

    void deleteMember(int memberNo);

	// TODO
    MemberResponse findByMemberNo(int memberNo);


}
