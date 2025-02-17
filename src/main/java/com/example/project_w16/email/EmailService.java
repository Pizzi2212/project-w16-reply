package com.example.project_w16.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject) throws MessagingException {
        sendEmail(to, subject, "mail di spam");
    }

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        if (body==null) body = "mail di default";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body, true);
        helper.setFrom("your-email@gmail.com");

        mailSender.send(message);
        System.out.println("Email inviata con successo a " + to);
    }
}
