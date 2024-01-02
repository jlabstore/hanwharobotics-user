package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.dto.MemberRequest;
import com.hanwha.robotics.user.entity.Member;

public interface MemberService {
    /**
     * 회원가입
     * @param memberRequest
     */
    void registerMember(MemberRequest memberRequest);

    /**
     * 회원가입 아이디 중복체크
     * @param memberId
     * @return
     */
    boolean isMemberIdExists(String memberId);

    /**
     * 회원 로그인
     * @param memberId
     * @param password
     * @return
     */
    Member login(String memberId, String password);
}
