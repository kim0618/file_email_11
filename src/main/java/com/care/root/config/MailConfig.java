package com.care.root.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
	public class MailConfig {
	    @Bean
	    public static JavaMailSender mailSender() {
	   JavaMailSenderImpl jms = new JavaMailSenderImpl();
	   jms.setHost("smtp.gmail.com");	// google smtp 서버 설정
	   jms.setPort(587);				// google에서 제공하는 메일 포트
	   jms.setUsername("tjd6189@gmail.com");	// 계정
	   jms.setPassword("rlawlstjd1!");				// 비번

	   Properties prop = new Properties();
	   prop.setProperty("mail.transport.protocol", "smtp");	// smtp 프로토콜을 사용하겠따
	   prop.setProperty("mail.smtp.auth", "true");  		// 로그인 할건지
	   prop.setProperty("mail.smtp.starttls.enable", "true"); // 보안처리 한건지
	   prop.setProperty("mail.debug", "true");				// 성공, 실패 알려줌
	   jms.setJavaMailProperties(prop);

	   return jms;
	    }
	}

