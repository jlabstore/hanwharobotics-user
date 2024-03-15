package com.hanwha.robotics.user.service;

import com.hanwha.robotics.user.dto.MemberRequest;
import com.hanwha.robotics.user.dto.MemberResponse;
import com.hanwha.robotics.user.dto.ResetPasswordRequest;
import com.hanwha.robotics.user.entity.Member;

public interface MemberService {
    /**
     * 회원가입
     * @param request
     */
    void registerMember(MemberRequest request);

    /**
     * 회원가입 아이디 중복체크
     * @param memberId
     * @return
     */
    boolean isMemberIdExists(String memberId);

    /**
     * 회원가입 이메일 중복체크
     * @param email
     * @return
     */
    boolean isMemberEmailExists(String email);

    /**
     * 회원 로그인
     * @param memberId
     * @param password
     * @return
     */
    Member login(String memberId, String password);

    /**
     * 아이디 찾기 - 이메일로 전송
     * @param request
     */
    void findId(MemberRequest request);

    /**
     * 비밀번호 찾기 - 임시비밀번호 전송
     * @param request
     */
    void sendPasswordResetMail(MemberRequest request);

    void checkPassword(int memberNo, MemberRequest request);

    /**
     * 비밀번호 재설정
     * @param memberNo
     * @param request
     */
    void changePassword(int memberNo, MemberRequest request);

    /**
     * 비밀번호 이메일 재설정
     * @param request
     */
    void resetPassword(ResetPasswordRequest request);




    /**
     * 회원 탈퇴
     * @param memberNo
     */
    void deleteAccount(int memberNo);

    MemberResponse retrieve(int memberNo);


    // 회원 정보 수정
    void updateMember(MemberRequest request);


    String getMemberEmail(int memberNo);
}
