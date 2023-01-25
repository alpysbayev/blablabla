package com.prometeo.drp_final.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${server.url}")
    private String url;

    public MailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendMessage(String toEmail, String subject, String body,String confirmationKey){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("ivorycost1989@gmail.com");
        msg.setTo(toEmail);

        msg.setSubject(subject);

        String text = body.concat(url).
                concat( confirmationKey);

        msg.setText(text);
        mailSender.send(msg);
    }
}

