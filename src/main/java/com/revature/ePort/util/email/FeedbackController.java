package com.revature.ePort.util.email;

import com.revature.ePort.util.annotations.Inject;
import com.revature.ePort.util.custom_exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Inject
    EmailConfigure emailConfigure;

    @Inject
    @Autowired
    public FeedbackController(EmailConfigure emailConfigure) {
        this.emailConfigure = emailConfigure;
    }

    @PostMapping
    public void sendFeedback(@RequestBody Feedback feedback, BindingResult bindResult){
        if(bindResult.hasErrors()) throw new InvalidRequestException("Feedback is not valid");

        //create the sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailConfigure.getHost());
        mailSender.setPort(this.emailConfigure.getPort());
        mailSender.setUsername(this.emailConfigure.getUsername());
        mailSender.setPassword(this.emailConfigure.getPassword());

        //create mail instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(feedback.getEmail());
        mailMessage.setTo("asd@gmail.com");
        mailMessage.setSubject("New feedback from "+feedback.getName());
        mailMessage.setText(feedback.getFeedback());

        //send mail
        mailSender.send(mailMessage);
    }
}
