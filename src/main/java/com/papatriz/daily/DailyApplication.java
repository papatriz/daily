package com.papatriz.daily;

import com.papatriz.daily.entity.User;
import com.papatriz.daily.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DailyApplication {

    @Autowired
    private final UserService userService;

    public DailyApplication(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DailyApplication.class, args);
    }

    @Bean
    public User testUser() {
        return userService.getTestUser().orElseThrow();
    }
   /* @Bean
    public PasswordEncoder passwordEncoderBCrypt() {
        return new BCryptPasswordEncoder();
    }*/
}
