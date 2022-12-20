package com.programmer.JwtBackend.email;

import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmailSimpleMessage(String to, String subject, String text) throws MessagingException;
}
