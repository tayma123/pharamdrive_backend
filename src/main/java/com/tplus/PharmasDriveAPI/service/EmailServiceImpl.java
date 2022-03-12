package com.tplus.PharmasDriveAPI.service;

import com.tplus.PharmasDriveAPI.model.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;




@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender ;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender ;
    }

    @Override
    @Async
    public void sendCodeByEmail(Mail email) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("PharmasDrive@gmail.com");
        simpleMailMessage.setTo(email.getTo());
        simpleMailMessage.setSubject("Code Active");
        simpleMailMessage.setText(email.getCode());
        //javaMailSender.send(simpleMailMessage);
        javaMailSender.send(simpleMailMessage);
    }
    
}
