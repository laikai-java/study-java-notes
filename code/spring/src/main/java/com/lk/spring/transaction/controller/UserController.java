package com.lk.spring.transaction.controller;


import com.lk.generator.entity.User;
import com.lk.spring.transaction.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/get/{id}")
    public User get(@PathVariable("id") Integer id){
        return userService.selectByPrimaryKey(id);
    }
}
