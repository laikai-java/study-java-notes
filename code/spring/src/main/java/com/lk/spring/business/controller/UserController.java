package com.lk.spring.business.controller;


import com.lk.generator.entity.User;
import com.lk.spring.business.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public User get(@PathVariable("id") Integer id) {
        return userService.selectByPrimaryKey(id);
    }

    @GetMapping("/get/all")
    public List<User> getAll() {
        return userService.selectAll();
    }

    @GetMapping("/insert")
    public Integer insert(User user) {
       userService.insert(user);
        return 1;
    }
}
