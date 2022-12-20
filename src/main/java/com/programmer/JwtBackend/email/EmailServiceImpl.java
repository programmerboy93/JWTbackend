package com.programmer.JwtBackend.email;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String author;

    @Override
    public void sendEmailSimpleMessage(String recipient, String subject, String content) throws MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(author);
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(content);

        emailSender.send(message);
    }
}
