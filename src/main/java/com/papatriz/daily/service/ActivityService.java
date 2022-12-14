package com.papatriz.daily.service;

import com.papatriz.daily.entity.Activity;
import com.papatriz.daily.entity.User;
import com.papatriz.daily.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Set<Activity> getActivitiesByUser(User user) {
        return activityRepository.findAllByUser(user);
    }
}
