package com.papatriz.daily.controller;

import com.papatriz.daily.dto.ActivityDto;
import com.papatriz.daily.entity.Activity;
import com.papatriz.daily.entity.User;
import com.papatriz.daily.service.ActivityService;
import com.papatriz.daily.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final UserService userService;
    private final ActivityService activityService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public ActivityController(UserService userService, ActivityService activityService) {
        this.userService = userService;
        this.activityService = activityService;
    }

    @GetMapping("")
    List<ActivityDto> getActivitiesForUser() {
        User testUser = userService.getTestUser().orElseThrow();
        List<ActivityDto> result = activityService.getActivitiesByUser(testUser);
        logger.info("ACTIVITY FOR USER "+testUser.getName()+" : "+result.size());
        return result;
    }

    @PostMapping("/add")
    public ResponseEntity<String> createActivity(
            @RequestBody ActivityDto activityDto){
        User testUser = userService.getTestUser().orElseThrow(); // toDo: move testUser to class
        Activity entity = new Activity();
        entity.setUser(testUser);
        entity.setTitle(activityDto.title());
        entity.setDuration(activityDto.duration());
        entity.setWeight(activityDto.weight());

        activityService.save(entity);
        return ResponseEntity.ok().body(
                "{\"activityID\":\""+entity.getId()+"\"}");
    }
}
