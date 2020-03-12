package com.omnia.docclassifier;

import com.omnia.docclassifier.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableConfigurationProperties(AppConfig.class)
public class DocClassifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocClassifierApplication.class, args);
    }

    /**
     * do NOT move this into WebSecurityConfig or you get a circular reference error
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
