package com.example.lms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Configure the SMTP server properties
        mailSender.setHost("smtp.example.com");  // SMTP server host
        mailSender.setPort(587);  // SMTP port (usually 587 for TLS, 465 for SSL)

        // Provide your email credentials
        mailSender.setUsername("your-email@example.com");
        mailSender.setPassword("your-email-password");

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");  // Optional for debugging

        return mailSender;
    }
}
