package com.hanwha.robotics.user.common.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import com.hanwha.robotics.user.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.hanwha.robotics.user.entity.Member;
import com.hanwha.robotics.user.mapper.PasswordResetTokenMapper;

@Component
public class MailUtil {

	@Value("${spring.mail.username}")
	private String hostname;

	@Value("${target.mail}")
	private String target;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private PasswordResetTokenMapper passwordResetTokenMapper;

	@Autowired
	private AdminMapper adminMapper;

	public Boolean sendMail(Map<String,Object> params){
		Boolean result = false;
		try{
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false , "UTF-8");
			messageHelper.setTo(target); // 메일 수신자
			messageHelper.setSubject("한화로보틱스 문의 메일"); // 메일 제목
			messageHelper.setText(setContext(params), true); // 메일 본문 내용, HTML 여부
			javaMailSender.send(mimeMessage);
			result = true;
		}catch(Exception e){
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	public String setContext(Map<String, Object> params) {
		Context context = new Context();
		context.setVariables(params);
		return templateEngine.process("inquiryTemplete", context);
	}



	private void sendEmail(List<String> targets, String subject, String text) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

			String[] emailArray = targets.toArray(new String[0]);
			messageHelper.setTo(emailArray);
			messageHelper.setSubject(subject);
			messageHelper.setText(text, true);
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new RuntimeException("메일발송 에러발생", e);
		}
	}

	@Async
	public void sendMemberId(String email, String memberId) {
//		try {
//			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
//			messageHelper.setTo(email); // 메일 수신자
//			messageHelper.setSubject("문의하신 한화로보틱스 아이디 안내 입니다.");
//			messageHelper.setText("아이디는 " + memberId + " 입니다.");
//			javaMailSender.send(mimeMessage);
//		} catch (Exception e) {
//			throw new RuntimeException("아이디 발송 실패", e);
//		}

		this.sendEmail(
				List.of(email),
				"문의하신 한화로보틱스 아이디 안내입니다.",
				"아이디는 " + memberId + " 입니다."
		);
	}

	@Async
	public void sendPasswordResetLink(Member member) {
		String resetToken = generateResetToken(member);
		String resetLink = "http://localhost:8081/password/reset?token=" +
			URLEncoder.encode(resetToken, StandardCharsets.UTF_8);

		this.sendEmail(
			List.of(member.getEmail()),
			"한화 로보틱스 비밀번호 재설정",
			"비밀번호 재설정 링크 : <a href=\"" + resetLink + "\">resetLink</a>"
		);
	}

	private String generateResetToken(Member member) {
		String resetToken = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
		Map<String, Object> map = new HashMap<>();
		map.put("memberNo", member.getMemberNo());
		map.put("token", resetToken);
		map.put("expiredDt", LocalDateTime.now().plusMinutes(10));
		map.put("creator", member.getMemberId());
		passwordResetTokenMapper.insertToken(map);
		return resetToken;
	}

	@Async
	public void sendNewQnaToAdmin() {
		try {
			this.sendEmail(
				adminMapper.selectAdminEmail(),
				"Q&A 게시판에 새로운 글이 등록되었습니다.",
				"Q&A 게시판에 새로운 글이 등록되었습니다."
			);
		} catch (Exception e) {
			throw new RuntimeException("관리자 이메일 발송 실패", e);
		}
	}

	@Async
	public void sendNewQnaReplyToAdmin() {
		try {
			this.sendEmail(
				adminMapper.selectAdminEmail(),
				"Q&A 게시글에 새로운 답글이 등록되었습니다.",
				"Q&A 게시글에 새로운 답글이 등록되었습니다."
			);
		} catch (Exception e) {
			throw new RuntimeException("관리자 이메일 발송 실패", e);
		}
	}
}
