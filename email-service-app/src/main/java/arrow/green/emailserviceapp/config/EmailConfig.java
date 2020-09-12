package arrow.green.emailserviceapp.config;

import java.util.Map;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nakulgoyal
 *         06/09/20
 **/

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "email.service")
public class EmailConfig {
    
    private Map<String, String> props;
    
    @Setter(AccessLevel.NONE)
    private String username;
    
    public Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", props.getProperty("smtp_auth"));
        props.put("mail.smtp.starttls.enable", props.getProperty("starttls_enabled"));
        props.put("mail.smtp.host", props.getProperty("host"));
        props.put("mail.smtp.port", props.getProperty("port"));
        return Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("username"), props.getProperty("password"));
            }
        });
    }
    
    @PostConstruct
    public void init() {
        this.username = props.get("username");
        log.info("Email config props : {}", props);
    }
    
}


