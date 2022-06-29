package com.revature.ePort.util.email;

import com.revature.ePort.util.annotations.Inject;
import com.revature.ePort.util.custom_exception.InvalidRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.xml.bind.ValidationException;
import java.util.Properties;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public EmailService() {
    }

    public void sendFeedback(String subject, String email, String body) {
        //create mail instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("ePortBackend@gmail.com");
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        //send mail
        mailSender.send(mailMessage);
    }
}

/*JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailConfigure.getHost());
        mailSender.setPort(emailConfigure.getPort());
        mailSender.setUsername(emailConfigure.getUsername());
        mailSender.setPassword(emailConfigure.getPassword());


        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put( "mail.smtp.host" , "smtp.gmail.com");
        props.put( "mail.smtp.user" , "ePortBackend@gmail.com" );
        props.put( "mail.smtp.password" , "Dog_Herding_SCP-1006" );

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailConfigure.getUsername(), emailConfigure.getPassword());
            }
        });


        try{
            //create mail instance
            MimeMessage mailMessage = new MimeMessage(session);
            mailMessage.setFrom(emailConfigure.getHost());
            mailMessage.setRecipients(Message.RecipientType.TO ,"baviles599@gmail.com");
            mailMessage.setSubject("New feedback from "+feedback.getName());
            mailMessage.setText(feedback.getFeedback());

            Transport transport = session.getTransport("smtp");
            transport.send(mailMessage);
        }catch(Exception x){
            System.out.println(x.getMessage());
        }*/

