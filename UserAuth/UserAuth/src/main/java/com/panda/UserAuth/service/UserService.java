package com.panda.UserAuth.service;

import com.panda.UserAuth.model.UserEntity;
import com.panda.UserAuth.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserEntity addUser() {

        return userRepository.save(UserEntity.builder()
                .userId(23)
                .userName("gudu")
                .pass(passwordEncoder.encode("gudu@123"))
                        .role("USER")
                .build());
    }

}
