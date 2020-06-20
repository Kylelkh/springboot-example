package com.kylelkh.springboot.example.controller;

import com.kylelkh.springboot.example.entity.User;
import com.kylelkh.springboot.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<Object> user(@RequestBody User user) {
        System.out.println("user is: " + user);
        userService.save(user);
        return ResponseEntity.ok(user);
    }
}
