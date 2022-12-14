package com.papatriz.daily.controller;

import com.papatriz.daily.entity.User;
import com.papatriz.daily.service.TasklogService;
import com.papatriz.daily.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    private final TasklogService tasklogService;
    private final UserService userService;
    @Autowired
    public TestController(TasklogService tasklogService, UserService userService) {
        this.tasklogService = tasklogService;
        this.userService = userService;
    }

    @GetMapping("/count")
    public long countOfCompleteActivities() {

        return tasklogService.getCompleteActivityCount(2L);
    }

    @GetMapping("/makeuser")
    public User makeUser() {
        User newUser = User.builder()
                .name("Gena")
                .surname("Tsodikov")
                .username("papatriz")
                .password("pass")
                .build();

        return userService.saveUser(newUser);
    }


}
