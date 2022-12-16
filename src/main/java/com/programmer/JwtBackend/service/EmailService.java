package com.programmer.JwtBackend.service;

import com.programmer.JwtBackend.domain.AppUser;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text) throws MessagingException;
}
