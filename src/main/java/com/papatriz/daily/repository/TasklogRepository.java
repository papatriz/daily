package com.papatriz.daily.repository;

import com.papatriz.daily.entity.Tasklog;
import com.papatriz.daily.entity.TasklogId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasklogRepository extends JpaRepository<Tasklog, TasklogId> {
}