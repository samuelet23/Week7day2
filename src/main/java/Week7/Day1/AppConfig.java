package Week7.Day1;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource("application.properties")
public class AppConfig {

    @Bean
    public Cloudinary getCloudinary(@Value("${cloudinary.cloud_name}") String name,
                                    @Value("${cloudinary.api_key}") String key,
                                    @Value("${cloudinary.api_secret}") String secret){
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", name,
                "api_key",key,
                "api_secret", secret));
    }

    @Bean
    public JavaMailSenderImpl getEmail(
            @Value("${gmail.host}") String host,
            @Value("${gmail.port}") int port,
            @Value("${gmail.username}") String username,
            @Value("${gmail.password}") String password,
            @Value("${gmail.props.protocol}") String protocol,
            @Value("${gmail.props.auth}") String auth,
            @Value("${gmail.props.starttls}") String starttls,
            @Value("${gmail.props.debug}") String debug,
            @Value("${gmail.props.ssl}") String ssl){

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.debug", debug);
        props.put("smtp.ssl.enable",ssl);
        return mailSender;
    }
}
