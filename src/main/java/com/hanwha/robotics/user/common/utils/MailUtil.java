package com.hanwha.robotics.user.common.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.hanwha.robotics.user.common.enums.NewsroomType;
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

//    public String sendTempPassword(String email) {
//        String tempPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
//		try {
//			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
//			messageHelper.setTo(email); // 메일 수신자
//			messageHelper.setSubject("문의하신 한화로보틱스 임시 패스워드 안내 입니다."); // 메일 제목
//			messageHelper.setText("임시비밀번호는 " + tempPassword + "입니다."); // 메일 본문 내용, HTML 여부
//			javaMailSender.send(mimeMessage);
//		} catch (Exception e) {
//			throw new RuntimeException("임시 패스워드 이메일 발송 실패", e);
//		}
//        return tempPassword;
//    }



	public void sendMemberId(String email, String memberId) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			messageHelper.setTo(email); // 메일 수신자
			messageHelper.setSubject("문의하신 한화로보틱스 아이디 안내 입니다."); // 메일 제목
			messageHelper.setText("아이디는 " + memberId + " 입니다."); // 메일 본문 내용, HTML 여부
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new RuntimeException("아이디 발송 실패", e);
		}
	}
    
	public String setContext(Map<String, Object> params) {
		Context context = new Context();
		context.setVariables(params);
		return templateEngine.process("inquiryTemplete", context);
	}


//	public String sendPasswordResetLink(String email) {
//		String resetToken = generateResetToken();
//
//		try {
//			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
//
//			String resetLink = "https://localhost:8081/password/reset?token=" + resetToken;
//			messageHelper.setTo(email);
//			messageHelper.setSubject("한화 로보틱스 비밀번호 재설정");
//			messageHelper.setText("비밀번호 재설정 링크 : <a href=\"" + resetLink + "\">" + resetLink + "</a>", true);
//
//			javaMailSender.send(mimeMessage);
//		} catch (Exception e) {
//			throw new RuntimeException("에러발생", e);
//		}
//
//		return resetToken;
//	}

	public String sendPasswordResetLink(String email) {
		String resetToken = generateResetToken();

		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

			String resetLink = "http://localhost:8081/password/reset?token=" +
					URLEncoder.encode(resetToken, StandardCharsets.UTF_8);

			messageHelper.setTo(email);
			messageHelper.setSubject("한화 로보틱스 비밀번호 재설정");
			messageHelper.setText("비밀번호 재설정 링크 : <a href=\"" + resetLink + "\">resetLink</a>", true);

			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new RuntimeException("에러발생", e);
		}
		return resetToken;
	}


	private String generateResetToken() {
		String resetToken = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
		Map<String, Object> map = new HashMap<>();
		map.put("token", resetToken);
		map.put("expiredDt", LocalDateTime.now().plusMinutes(10));
		passwordResetTokenMapper.insertToken(map);
		return resetToken;
	}

}
