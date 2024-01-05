package com.hanwha.robotics.user.service.impl;

import java.util.Optional;

import com.hanwha.robotics.user.common.utils.MailUtil;
import com.hanwha.robotics.user.dto.MemberRequest;
import com.hanwha.robotics.user.entity.Member;
import com.hanwha.robotics.user.mapper.MemberLogMapper;
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
    private MemberLogMapper memberLogMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailUtil mailUtil;

    @Override
    public void registerMember(MemberRequest request) {
        request.encryptedPassword(passwordEncoder);
        Member member = request.toEntity();
        memberMapper.insertMember(member);
    }

    @Override
    public boolean isMemberIdExists(String memberId) {
        int count = memberMapper.checkMemberId(memberId);
        return count == 1;
    }

    @Override
    public Member login(String memberId, String password) {
        Member member = Optional.ofNullable(memberMapper.selectByMemberId(memberId))
            .orElseThrow(() -> new RuntimeException("회원정보를 찾을 수 없습니다."));
        if (member.getAcceptYn().equals("N")) {
            throw new RuntimeException("승인전 계정입니다.");
        }
        memberLogMapper.insertMemberLog(member);
        return member;
    }

    @Override
    public void findId(MemberRequest request) {
        Member member = Optional.ofNullable(memberMapper.selectByNameAndEmail(request.getName(), request.getEmail()))
                .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없음"));
        String memberId = member.getMemberId();
        mailUtil.sendMemberId(member.getEmail(), memberId);
    }

    @Override
    public void findPassword(MemberRequest request) {
        Member member = Optional.ofNullable(memberMapper.selectByMemberIdAndEmail(request.getMemberId(), request.getEmail()))
            .orElseThrow(() -> new RuntimeException("회원을 찾을 수 없음"));
        String tempPassword = mailUtil.sendTempPassword(member.getEmail());
        String encodedPassword = passwordEncoder.encode(tempPassword);
        memberMapper.updatePassword(member.getMemberId(), encodedPassword);
    }


}
