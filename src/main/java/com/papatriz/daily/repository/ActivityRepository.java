package com.papatriz.daily.repository;

import com.papatriz.daily.entity.Activity;
import com.papatriz.daily.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findAllByUser(User user);
}