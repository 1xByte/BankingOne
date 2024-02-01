package com.panda.UserAuth.repo;

import com.panda.UserAuth.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    Optional<UserEntity> findByUserName(String username);
}
