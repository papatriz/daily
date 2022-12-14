package com.papatriz.daily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DailyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyApplication.class, args);
    }

   /* @Bean
    public PasswordEncoder passwordEncoderBCrypt() {
        return new BCryptPasswordEncoder();
    }*/
}
