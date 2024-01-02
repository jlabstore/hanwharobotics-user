package com.hanwha.robotics.user.mapper;

import com.hanwha.robotics.user.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void insertMember(Member member);
    int checkMemberId(String memberId);
    Member selectByMemberId(String memberId);
}
