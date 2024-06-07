package com.hanwha.robotics.user.common.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.InternetAddress;
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

	@Value("${base.url}")
	private String baseUrl;

	@Value("${base.url.en}")
	private String baseUrlEn;

	@Value("${base.admin.url}")
	private String adminUrl;

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
			String targetMail = (String) params.get("targetMail");
			messageHelper.setTo(targetMail); // 메일 수신자
			// messageHelper.setTo(target); // 메일 수신자
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
			messageHelper.setFrom(new InternetAddress(target, "한화로보틱스"));
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new RuntimeException("메일발송 에러발생", e);
		}
	}

//	@Async
//	public void sendMemberId(String email, String memberId, String region) {
//		Context context = new Context();
//		context.setVariable("baseUrl", baseUrl);
//		context.setVariable("baseUrlEn", baseUrlEn);
//		context.setVariable("memberId", memberId);
//
//		String template = region.equals("KR") ? "email/email_id" : "email/email_id_en";
//		String subject = region.equals("KR") ? "문의하신 한화로보틱스 아이디 안내입니다." : "Hanwha Robotics | Find ID";
//
//		String emailContent = templateEngine.process(template, context);
//		this.sendEmail(List.of(email), subject, emailContent);
//	}


	@Async
	public void sendMemberId(String email, String memberId, String region, String locale) {
		Context context = new Context();
		context.setVariable("baseUrl", baseUrl);
		context.setVariable("baseUrlEn", baseUrlEn);
		context.setVariable("memberId", memberId);

//		String template = region.equals("KR") ? "email/email_id" : "email/email_id_en";
//		String subject = region.equals("KR") ? "문의하신 한화로보틱스 아이디 안내입니다." : "Hanwha Robotics | Find ID";

		String template = locale.equals("ko") ? "email/email_id" : "email/email_id_en";
		String subject = locale.equals("ko") ? "문의하신 한화로보틱스 아이디 안내입니다." : "Hanwha Robotics | Find ID";

		String emailContent = templateEngine.process(template, context);
		this.sendEmail(List.of(email), subject, emailContent);
	}





	@Async
	public void sendPasswordResetLink(Member member) {
		String resetToken = generateResetToken(member);
		String resetLink = String.format("%s/password/reset?token=%s", baseUrl, URLEncoder.encode(resetToken, StandardCharsets.UTF_8));
		String resetLinkEn = String.format("%s/password/reset?token=%s", baseUrlEn, URLEncoder.encode(resetToken, StandardCharsets.UTF_8));

		Context context = new Context();
		context.setVariable("baseUrl", baseUrl);
		context.setVariable("baseUrlEn", baseUrlEn);
		context.setVariable("resetLink", resetLink);
		context.setVariable("resetLinkEn", resetLinkEn);

		String template = member.getRegion().equals("KR") ? "email/email_password_re" : "email/email_password_re_en";
		String subject = member.getRegion().equals("KR") ? "한화로보틱스 비밀번호 재설정 안내입니다." : "Hanwha Robotics | Password Reset";

		String emailContent = templateEngine.process(template, context);
		this.sendEmail(List.of(member.getEmail()), subject, emailContent);
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
	public void sendPasswordChangeConfirm(String email, String region) {
		Context context = new Context();
		context.setVariable("baseUrl", baseUrl);
		context.setVariable("baseUrlEn", baseUrlEn);

		String template = region.equals("KR") ? "email/email_password_re" : "email/email_password_re_en";
		String subject = region.equals("KR") ? "한화로보틱스 고객님의 비밀번호가 변경되었습니다." : "Hanwha Robotics | Password Change Notification";

		String emailContent = templateEngine.process(template, context);
		this.sendEmail(List.of(email), subject, emailContent);
	}


	@Async
	public void sendNewQnaToAdmin(int qnaNo) {
		try {
			String qnaLink = adminUrl + "/admin/qna/detail?no=" + qnaNo;
			Context context = new Context();
			context.setVariable("qnaLink", qnaLink);
			String emailContent = templateEngine.process("email/email_post", context);

			this.sendEmail(
				adminMapper.selectAdminEmail(),
				"Q&A 게시판에 새로운 글이 등록되었습니다.",
					emailContent
			);
		} catch (Exception e) {
			throw new RuntimeException("관리자 이메일 발송 실패", e);
		}
	}

	@Async
	public void sendNewQnaReplyToAdmin(int qnaNo) {
		try {
			String qnaLink = adminUrl + "/admin/qna/detail?no=" + qnaNo;
			Context context = new Context();
			context.setVariable("qnaLink", qnaLink);
			String emailContent = templateEngine.process("email/email_reply", context);

			this.sendEmail(
				adminMapper.selectAdminEmail(),
				"Q&A 게시글에 새로운 댓글이 등록되었습니다.",
					emailContent
			);
		} catch (Exception e) {
			throw new RuntimeException("관리자 이메일 발송 실패", e);
		}
	}
}
