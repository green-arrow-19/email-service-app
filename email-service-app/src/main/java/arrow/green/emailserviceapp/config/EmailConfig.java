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
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", this.props.get("smtp_auth"));
        properties.put("mail.smtp.starttls.enable", this.props.get("starttls_enabled"));
        properties.put("mail.smtp.host", this.props.get("host"));
        properties.put("mail.smtp.port", this.props.get("port"));
        return Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.get("username"), props.get("password"));
            }
        });
    }
    
    @PostConstruct
    public void init() {
        this.username = props.get("username");
        log.info("Email config props : {}", props);
    }
    
}


