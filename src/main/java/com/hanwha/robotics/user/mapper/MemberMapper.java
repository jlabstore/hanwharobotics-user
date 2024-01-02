package com.hanwha.robotics.user.mapper;

import com.hanwha.robotics.user.entitiy.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void insertMember(Member member);

}
