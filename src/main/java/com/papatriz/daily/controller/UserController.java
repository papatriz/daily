package com.papatriz.daily.controller;

import com.papatriz.daily.dto.UserDto;
import com.papatriz.daily.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private User user;

    @GetMapping()
    public UserDto getUserData() {
        return new UserDto(user.getName(), user.getSurname());
    }
}
