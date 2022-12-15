package com.papatriz.daily.service;

import com.papatriz.daily.dto.ActivityDto;
import com.papatriz.daily.entity.User;
import com.papatriz.daily.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActivityService {
    private final ActivityRepository activityRepository;
    @Autowired
    public ActivityService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public Set<ActivityDto> getActivitiesByUser(User user) {
        return activityRepository.findAllByUser(user).stream()
                .map(activity -> new ActivityDto(activity.getTitle(), activity.getDuration(), activity.getWeight())).collect(Collectors.toSet());
    }
}
