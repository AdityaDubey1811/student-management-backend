package com.example.backenddemo.service;

import com.example.backenddemo.dto.LoginRequest;
import com.example.backenddemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {

    User saveUser(User user);
    User getUserByUsername(String username);
    String login(LoginRequest request);

}
