package com.emailutility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
// Class
// Implementing EmailService interface
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${spring.mail.list.of.strings}")
    private List<String> toList;

    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details) {


        try {
            for (String to : toList) {
                SimpleMailMessage mailMessage
                        = new SimpleMailMessage();
                // Setting up necessary details
                mailMessage.setFrom(sender);
                mailMessage.setText(details.getMsgBody());
                mailMessage.setSubject(details.getSubject());
                mailMessage.setTo(to);
                javaMailSender.send(mailMessage);
            }
            return "Mail Sent Successfully...";
        }
        // Catch block to handle the exceptions
        catch (Exception e) {
            System.out.println("Email error " + e);
            return "Error while Sending Mail";
        }
    }


    public String sendMailWithAttachment(EmailDetails details) {


        try {
            for (String to : toList) {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper;
                mimeMessageHelper
                        = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setTo(to);
                mimeMessageHelper.setFrom(sender);
                mimeMessageHelper.setText(details.getMsgBody());
                mimeMessageHelper.setSubject(details.getSubject());
                javaMailSender.send(mimeMessage);
                FileSystemResource file
                        = new FileSystemResource(
                        new File(details.getAttachment()));

                mimeMessageHelper.addAttachment(
                        file.getFilename(), file);

                javaMailSender.send(mimeMessage);

            }
            return "Mail sent Successfully to the whole list with attachments";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            System.out.println("Error message " + e);
            return "Error while sending mail!!!";
        }
    }
}
