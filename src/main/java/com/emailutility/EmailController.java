package com.emailutility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
// Class
public class EmailController {
    @Autowired
    private EmailService emailService;
    // Sending a simple Email
    @PostMapping("/sendMail")
    public String sendMail(@RequestBody EmailDetails details)
    {
        return  emailService.sendSimpleMail(details);

    }
    // Sending email with attachment
    @PostMapping("/sendMailWithAttachment")
    public String sendMailWithAttachment( @RequestBody EmailDetails details)
    {
        return emailService.sendMailWithAttachment(details);
    }
}
