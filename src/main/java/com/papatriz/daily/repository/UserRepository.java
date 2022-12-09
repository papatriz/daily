package com.papatriz.daily.repository;

import com.papatriz.daily.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}