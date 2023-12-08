package com.store.demo.configuration;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com"); // Thay thế bằng địa chỉ máy chủ email của bạn
        mailSender.setPort(587); // Thay thế bằng cổng máy chủ email của bạn
        mailSender.setUsername("anthps21679@fpt.edu.vn"); // Thay thế bằng tên người dùng của bạn
        mailSender.setPassword("bqqq umun qpef rtww"); // Thay thế bằng mật khẩu của bạn

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}