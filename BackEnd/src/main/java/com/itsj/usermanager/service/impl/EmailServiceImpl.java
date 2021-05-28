package com.itsj.usermanager.service.impl;

import com.itsj.usermanager.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean sendEmail(String subject, String message, String to)
    {

        boolean foo = false; // Set the false, default variable "foo", we will allow it after sending code process email

        try {
            System.out.println("Sending email...");
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("tranminhtuannhj@gmail.com");
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(message);
            System.out.println("error...");
            javaMailSender.send(simpleMailMessage);
            System.out.println("Sending email successlly...");
            foo = true; // Set the "foo" variable to true after successfully sending emails
        }catch(Exception e){
            System.out.println("Sending mail error..." + e);
        }
        return foo; // and return foo variable
    }
}
