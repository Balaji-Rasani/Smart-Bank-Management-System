package com.bank.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    private final JavaMailSender mailSender;

    // Constructor injection (preferred)
    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String fromEmail;

    // Send simple OTP email
    public void sendOtpEmail(String toEmail, String subject, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setSubject(subject);
            message.setText("Your OTP code is: " + otp);

            mailSender.send(message);
            System.out.println("OTP sent successfully to " + toEmail);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
