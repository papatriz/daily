package com.papatriz.daily.controller;

import com.papatriz.daily.dto.ActivityDto;
import com.papatriz.daily.entity.Activity;
import com.papatriz.daily.entity.Tasklog;
import com.papatriz.daily.entity.TasklogId;
import com.papatriz.daily.entity.User;
import com.papatriz.daily.service.ActivityService;
import com.papatriz.daily.service.TasklogService;
import com.papatriz.daily.validator.ActivityRequestValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityService activityService;
    private final TasklogService tasklogService;
    private final ActivityRequestValidator validator;
    @Autowired
    private User testUser;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public ActivityController(ActivityService activityService, TasklogService tasklogService, ActivityRequestValidator validator) {
        this.activityService = activityService;
        this.tasklogService = tasklogService;
        this.validator = validator;
    }

    @GetMapping("")
    List<ActivityDto> getActivitiesForUser() {
        List<ActivityDto> result = activityService.getActivitiesByUser(testUser);
        logger.info("ACTIVITY FOR USER "+testUser.getName()+" : "+result.size());
        return result;
    }

    @GetMapping("/{id}/{date}")
    public boolean isActivityComplete(@PathVariable("id") Long id, @PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {

        return activityService.isActivityComplete(id, date);
    }

    @PostMapping("/{id}/{date}")
    public ResponseEntity<String> completeActivity(@PathVariable("id") Long id, @PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {

        var activityOpt = activityService.getById(id);
        if (activityOpt.isEmpty()) return ResponseEntity.notFound().build();;

        var taskKey = new TasklogId(id, date);
        var task = new Tasklog();
        task.setId(taskKey);
        task.setActivity(activityOpt.get());
        tasklogService.save(task);

        return ResponseEntity.ok().body("complete");

    }

    @PostMapping("/add")
    public ResponseEntity<String> createActivity(@RequestBody ActivityDto activityDto) {

        List<String> errors = validator.validate(activityDto);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(String.join("\n", errors));
        }

        Activity entity = new Activity();

        entity.setUser(testUser);
        entity.setTitle(activityDto.getTitle());
        entity.setDuration(activityDto.getDuration());
        entity.setWeight(activityDto.getWeight());
        entity.setStartDate(LocalDate.now());

        activityService.save(entity);
        return ResponseEntity.ok().body(
                "{\"activityId\":\""+entity.getId()+"\"}");
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editActivity(@RequestBody ActivityDto dto) {

        logger.info(dto.toString());
        List<String> errors = validator.validate(dto);
        if (!errors.isEmpty()) {
            return ResponseEntity.badRequest().body(String.join("\n", errors));
        }

        Optional<Activity> entityOpt = activityService.getById(dto.getId());

        if (!entityOpt.isPresent())
            return ResponseEntity.notFound().build();

        Activity entity = entityOpt.get();
        entity.setTitle(dto.getTitle());
        entity.setDuration(dto.getDuration());
        entity.setWeight(dto.getWeight());

        activityService.save(entity);
        return ResponseEntity.ok().body(
                "{\"activityId\":\""+entity.getId()+"\"}");
    }

    @DeleteMapping("/{id}")
    public void deleteActivity(@PathVariable("id") Long id) {
        activityService.deleteById(id);
    }
}
