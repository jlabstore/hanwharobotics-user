package com.hanwha.robotics.user.service.impl;

import com.hanwha.robotics.user.common.utils.LocaleUtils;
import java.util.Locale;
import java.util.Optional;

import com.hanwha.robotics.user.common.enums.MemberLogType;
import com.hanwha.robotics.user.common.handler.exception.BadRequestException;
import com.hanwha.robotics.user.common.handler.exception.NotFoundException;
import com.hanwha.robotics.user.common.utils.MailUtil;
import com.hanwha.robotics.user.dto.MemberRequest;
import com.hanwha.robotics.user.dto.MemberResponse;
import com.hanwha.robotics.user.dto.ResetPasswordRequest;
import com.hanwha.robotics.user.entity.Member;
import com.hanwha.robotics.user.mapper.DeletedAccountMapper;
import com.hanwha.robotics.user.mapper.MemberMapper;
import com.hanwha.robotics.user.mapper.PasswordResetTokenMapper;
import com.hanwha.robotics.user.service.MemberService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.WebUtils;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private MemberLogService memberLogService;
	@Autowired
	private DeletedAccountMapper deletedAccountMapper;
	@Autowired
	private PasswordResetTokenMapper passwordResetTokenMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MailUtil mailUtil;
	@Autowired
	private MessageSource messageSource;

	// 회원가입
	@Override
	public void registerMember(MemberRequest request) {

		if (this.isMemberIdExists(request.getMemberId())) {
			throw new BadRequestException("Duplicate ID");
		}

		if (this.isMemberEmailExists(request.getEmail())){
			throw new BadRequestException("Duplicate Email");
		}

		request.encryptedPassword(passwordEncoder);
		Member member = request.toEntity();
		memberMapper.insertMember(member);
	}

	// 아이디 중복확인
	@Override
	public boolean isMemberIdExists(String memberId) {
		int count = memberMapper.checkMemberId(memberId);
		return count == 1;
	}

	// 이메일 중복확인
	@Override
	public boolean isMemberEmailExists(String email) {
		int count = memberMapper.checkMemberEmail(email);
		return count == 1;
	}

	// 로그인
//	@Override
//	public Member login(String memberId, String password) {
//		return Optional.ofNullable(memberMapper.selectByMemberId(memberId)).map(member -> {
//			if (!passwordEncoder.matches(password, member.getPassword())) {
//				throw new RuntimeException("아이디 혹은 패스워드가 일치하지 않습니다.");
//			}
//			if (member.getAcceptYn().equals("N")) {
//				throw new RuntimeException("승인전 계정입니다.");
//			}
//			memberLogService.insertMemberLog(member.getMemberNo(), MemberLogType.LOGIN);
//			return member;
//		}).orElseThrow(() -> new RuntimeException("회원정보를 찾을 수 없습니다."));
//	}

	@Override
	public Member login(String memberId, String password) {
		return Optional.ofNullable(memberMapper.selectByMemberId(memberId)).map(member -> {
			Locale locale = LocaleContextHolder.getLocale();
			String errorMessage;

			if (!passwordEncoder.matches(password, member.getPassword())) {
				errorMessage = (locale.equals(Locale.KOREAN)) ? "아이디 혹은 패스워드가 일치하지 않습니다." : "Invalid ID or password.";
				throw new RuntimeException(errorMessage);
			}

			if (member.getAcceptYn().equals("N")) {
				errorMessage = (locale.equals(Locale.KOREAN)) ? "승인전 계정입니다." : "Account not approved.";
				throw new RuntimeException(errorMessage);
			}

			memberLogService.insertMemberLog(member.getMemberNo(), MemberLogType.LOGIN);
			return member;
		}).orElseThrow(() -> {
			Locale locale = LocaleContextHolder.getLocale();
			String errorMessage = (locale.equals(Locale.KOREAN)) ? "회원정보를 찾을 수 없습니다." : "Member information not found.";
      return new RuntimeException(errorMessage);
		});
	}


	// 아이디 찾기
	@Override
	public void findId(MemberRequest request, HttpServletRequest httpRequest) {
		Locale locale = LocaleUtils.getLocaleFromCookie(httpRequest, "lang");
		Member member = Optional.ofNullable(memberMapper.selectByEmail(request.getEmail()))
				.orElseThrow(() -> new NotFoundException(
						messageSource.getMessage("error.member.notfound", null, locale)
				));
		mailUtil.sendMemberId(member.getEmail(), member.getMemberId(), member.getRegion());
	}


	// 비밀번호 찾기 - 이메일 링크 전송
	@Override
	public void sendPasswordResetMail(MemberRequest request, HttpServletRequest httpRequest) {
		Locale locale = LocaleUtils.getLocaleFromCookie(httpRequest, "lang");
		Member member = Optional.ofNullable(memberMapper.selectByMemberId(request.getMemberId()))
				.orElseThrow(() -> new NotFoundException(
						messageSource.getMessage("error.member.notfound", null, locale)
				));
		mailUtil.sendPasswordResetLink(member);
	}

	// 비밀번호 재설정
	@Override
	public void resetPassword(ResetPasswordRequest request) {
		Optional.ofNullable(passwordResetTokenMapper.findByToken(request.getToken()))
			.ifPresent(token -> {
				Member member = memberMapper.selectByMemberNo(token.getMemberNo());
				String encodedPassword = passwordEncoder.encode(request.getNewPassword());
				memberMapper.updatePassword(member.getMemberId(), encodedPassword);
			});
	}


	@Override
	public void checkPassword(int memberNo, MemberRequest request) {
		Member member = memberMapper.selectByMemberNo(memberNo);
		if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
			throw new BadRequestException("기존 비밀번호와 일치하지 않습니다.");
		}
	}

	// 마이페이지 - 비밀번호 변경
	@Override
	public void changePassword(int memberNo, MemberRequest request) {
		Member member = memberMapper.selectByMemberNo(memberNo);
//		if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
//			throw new BadRequestException("기존 비밀번호와 일치하지 않습니다.");
//		}
		String encodedPassword = passwordEncoder.encode(request.getNewPassword());
		memberMapper.updatePassword(member.getMemberId(), encodedPassword);
	}

	// 회원 정보
	@Override
	public MemberResponse retrieve(int memberNo) {
		return memberMapper.findByMemberNo(memberNo);
	}

	// 회원 정보 수정
	@Override
	public void updateMember(MemberRequest request) {
		Member member = request.toEntity();
		memberMapper.updateMember(member);

	}

	@Override
	public String getMemberEmail(int memberNo) {
		return memberMapper.findEmailByMemberNo(memberNo);
	}

	public MemberResponse getMemberEmailAndRegion(int memberNo) {
		Member member = memberMapper.findEmailAndRegionByMemberNo(memberNo).orElseThrow(() -> new RuntimeException("not found"));
		return new MemberResponse(member.getEmail(), member.getRegion());
	}

	// 회원 탈퇴
	@Override
	@Transactional
	public void deleteAccount(int memberNo) {
		deletedAccountMapper.insertDeletedAccount(memberNo);
		memberMapper.deleteMember(memberNo);
		SecurityContextHolder.clearContext();
	}

}
