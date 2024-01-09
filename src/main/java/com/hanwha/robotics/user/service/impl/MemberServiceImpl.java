package com.hanwha.robotics.user.service.impl;

import com.hanwha.robotics.user.dto.MemberRequest;
import com.hanwha.robotics.user.entity.Member;
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

    @Override
    public void registerMember(MemberRequest memberRequest) {
        memberRequest.encryptedPassword(passwordEncoder);
        Member member = memberRequest.toEntity();
        memberMapper.insertMember(member);
    }

    @Override
    public boolean isMemberIdExists(String memberId) {
        int count = memberMapper.checkMemberId(memberId);
        return count == 1;
    }

    @Override
    public Member login(String memberId, String password) {
        Member member = memberMapper.selectByMemberId(memberId);
        if (member == null || !passwordEncoder.matches(password, member.getPassword())) {
            // TODO : log 업데이트
            return null;
        }
        return member;
    }



}
