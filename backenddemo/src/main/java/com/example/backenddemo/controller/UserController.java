package com.example.backenddemo.controller;

import com.example.backenddemo.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.backenddemo.entity.User;
import com.example.backenddemo.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @GetMapping("/{username}")
    public User getUser(@PathVariable String username){
        return userService.getUserByUsername(username);
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){
        return userService.login(request);
    }

}
