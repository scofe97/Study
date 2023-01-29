package com.dsg.dockercomposetest.repository;

import com.dsg.dockercomposetest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
