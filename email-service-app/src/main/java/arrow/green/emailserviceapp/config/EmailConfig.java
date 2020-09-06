package arrow.green.emailserviceapp.config;

import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

/**
 * @author nakulgoyal
 *         06/09/20
 **/

@Data
@Configuration
public class EmailConfig {
    
    @Value("${email.service.props.username}")
    private String username;
    @Value("${email.service.props.password}")
    private String password;
    @Value("${email.service.props.smtp_auth}")
    private String smtp_auth;
    @Value("${email.service.props.starttls_enabled}")
    private String starttls_enabled;
    @Value("${email.service.props.host}")
    private String host;
    @Value("${email.service.props.port}")
    private String port;
    
    public Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", smtp_auth);
        props.put("mail.smtp.starttls.enable", starttls_enabled);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        
        return Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
    
}


