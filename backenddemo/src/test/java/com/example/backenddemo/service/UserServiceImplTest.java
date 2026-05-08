package com.example.backenddemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import com.example.backenddemo.entity.User;
import com.example.backenddemo.entity.Role;
import com.example.backenddemo.service.UserService;

@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Test
    void testSaveUser(){
        User user = new User();
        user.setUsername("test1");
        user.setPassword("pass");
        user.setRole(Role.STUDENT);

        User saved = userService.saveUser(user);

        assertNotNull(saved);
        assertEquals("test1",saved.getUsername());
    }
}
