package arrow.green.emailserviceapp.controller;

import java.io.IOException;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import arrow.green.emailserviceapp.service.EmailService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nakulgoyal
 *         06/09/20
 **/

@Slf4j
@RestController
public class EmailController {
    
    @Autowired
    private EmailService emailService;
    
    @GetMapping("/email/send")
    public ResponseEntity<?> sendMail(@RequestParam(value = "to") String to,
            @RequestParam(value = "subject") String subject, @RequestParam(value = "content") String content)
    throws IOException, MessagingException {
        log.info("Send email request --> to : {}, subject : {}, content : {}", to, subject, content);
        emailService.sendMail(to, subject, content);
        log.info("Email sent successfully to : {}, subject : {}", to, content);
        return ResponseEntity.ok("SUCCESS");
    }
}


