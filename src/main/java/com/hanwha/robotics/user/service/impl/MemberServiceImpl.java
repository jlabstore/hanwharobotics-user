package com.hanwha.robotics.user.service.impl;

import com.hanwha.robotics.user.dto.MemberRequest;
import com.hanwha.robotics.user.entitiy.Member;
import com.hanwha.robotics.user.mapper.MemberMapper;
import com.hanwha.robotics.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // TODO: 아이디? 대리점? 중복 확인?
    @Override
    public void registerMember(MemberRequest memberRequest) {
        memberRequest.encryptedPassword(passwordEncoder);
        Member member = memberRequest.toEntity();
        memberMapper.insertMember(member);
    }

}
