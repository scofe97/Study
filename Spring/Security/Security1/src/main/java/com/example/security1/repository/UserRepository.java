package com.example.security1.repository;

import com.example.security1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// CRUD 함수를 JpaRepository가 들고있음
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUsername(String username);

}