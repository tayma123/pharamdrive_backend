package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class Config {

    @Bean
    public static JavaMailSender javaMailSender() {
        return new JavaMailSenderImpl();
    }

}
