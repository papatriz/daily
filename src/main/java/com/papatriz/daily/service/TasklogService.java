package com.papatriz.daily.service;

import com.papatriz.daily.entity.Tasklog;
import com.papatriz.daily.repository.TasklogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasklogService {
    private final TasklogRepository repository;

    @Autowired
    public TasklogService(TasklogRepository repository) {
        this.repository = repository;
    }

    public long getCompleteActivityCount(long activityId) {
        return repository.findAllByActivityId(activityId).stream().count();
    }

    public void save(Tasklog task) {
        repository.save(task);
    }
}
