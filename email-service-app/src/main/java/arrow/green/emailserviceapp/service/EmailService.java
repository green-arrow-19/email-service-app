package arrow.green.emailserviceapp.service;

import java.io.IOException;
import java.util.Date;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import arrow.green.emailserviceapp.config.EmailConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nakulgoyal
 *         06/09/20
 **/

@Slf4j
@Service
public class EmailService {
    
    @Autowired
    private EmailConfig emailConfig;
    
    public void sendMail(String to, String subject, String content) throws MessagingException, IOException {
        
        Session session = emailConfig.getSession();
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(emailConfig.getUsername(), false));
        
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        msg.setSubject(subject);
        msg.setContent(content, "text/html");
        msg.setSentDate(new Date());
        
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/html");
        
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//
//        attachPart.attachFile("/home/nakulgoyal/Downloads/NakulGoyalPic.jpg");
//        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}


