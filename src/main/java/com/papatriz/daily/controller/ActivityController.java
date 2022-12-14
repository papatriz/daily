package com.papatriz.daily.controller;

import com.papatriz.daily.entity.Activity;
import com.papatriz.daily.entity.User;
import com.papatriz.daily.service.ActivityService;
import com.papatriz.daily.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final UserService userService;
    private final ActivityService activityService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public ActivityController(UserService userService, ActivityService activityService) {
        this.userService = userService;
        this.activityService = activityService;
    }

    @GetMapping("")
    Set<Activity> getActivitiesForUser() {
        User testUser = userService.getTestUser().orElseThrow();
        Set<Activity> result = activityService.getActivitiesByUser(testUser);
        logger.info("ACTIVITY FOR USER "+testUser.getName()+" : "+result.size());
        return result;
    }
}
