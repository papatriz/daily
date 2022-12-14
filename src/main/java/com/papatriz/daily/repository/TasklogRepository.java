package com.papatriz.daily.repository;

import com.papatriz.daily.entity.Activity;
import com.papatriz.daily.entity.Tasklog;
import com.papatriz.daily.entity.TasklogId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface TasklogRepository extends JpaRepository<Tasklog, TasklogId> {

    List<Tasklog> findAllByActivityId(long id);
    Set<Tasklog> findAllByActivity(Activity activity);

    List<Tasklog> findAllById_Date(Date date);

}