package com.panda.UserAuth.cont;

import com.panda.UserAuth.model.UserEntity;
import com.panda.UserAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public UserEntity addUser(){

        return userService.addUser();
    }

    @GetMapping("/get")
    public String getUser(){

        return "Success";
    }

}
