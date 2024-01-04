package com.hanwha.robotics.user.common.utils;

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

    public String sendTempPassword(String email) {
        String tempPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			messageHelper.setTo(email); // 메일 수신자
			messageHelper.setSubject("한화로보틱스 임시 패스워드 발송메일"); // 메일 제목
			messageHelper.setText(tempPassword); // 메일 본문 내용, HTML 여부
			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new RuntimeException("임시 패스워드 이메일 발송 실패", e);
		}
        return tempPassword;
    }
    
	public String setContext(Map<String, Object> params) {
		Context context = new Context();
		context.setVariables(params);
		return templateEngine.process("inquiryTemplete", context);
	}
}
