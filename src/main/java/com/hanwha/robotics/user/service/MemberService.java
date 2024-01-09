package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.dto.MemberRequest;

public interface MemberService {
    /**
     * 회원가입
     * @param memberRequest
     */
    void registerMember(MemberRequest memberRequest);

}
