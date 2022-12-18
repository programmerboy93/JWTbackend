package com.programmer.JwtBackend.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("noreply@baeldung.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(prepareEmailRegister(), true);

        emailSender.send(message);
    }

    private String prepareEmailRegister() {

        return """
                Hello [name],
                Thank you for joining [customer portal].
                                
                We’d like to confirm that your account was created successfully. To access [customer portal] click the link below.
                                
                [Link/Button]
                                
                If you experience any issues logging into your account, reach out to us at [email address].
                                
                Best,
                The [customer portal] team
                """;
    }
}
