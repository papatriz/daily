package com.papatriz.daily.service;

import com.papatriz.daily.dto.ActivityDto;
import com.papatriz.daily.entity.Activity;
import com.papatriz.daily.entity.TasklogId;
import com.papatriz.daily.entity.User;
import com.papatriz.daily.repository.ActivityRepository;
import com.papatriz.daily.repository.TasklogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final TasklogRepository tasklogRepository;
    @Autowired
    public ActivityService(ActivityRepository activityRepository, TasklogRepository tasklogRepository) {
        this.activityRepository = activityRepository;
        this.tasklogRepository = tasklogRepository;
    }

    public List<ActivityDto> getActivitiesByUser(User user) {
        return activityRepository.findAllByUser(user).stream()
                .map(activity -> new ActivityDto(
                        activity.getId(),
                        activity.getTitle(),
                        activity.getDuration(),
                        activity.getWeight()))
                .collect(Collectors.toList());
    }

    public void save(Activity activity) {
        activityRepository.save(activity);
    }

    public boolean isActivityComplete(long id, LocalDate date) {
        return tasklogRepository.existsById(new TasklogId(id, date));
    }

    public Optional<Activity> getById(long id) {
        return activityRepository.findById(id);
    }
}
