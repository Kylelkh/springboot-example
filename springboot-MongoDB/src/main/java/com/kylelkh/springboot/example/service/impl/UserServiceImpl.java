package com.kylelkh.springboot.example.service.impl;

import com.kylelkh.springboot.example.entity.User;
import com.kylelkh.springboot.example.repository.UserRepository;
import com.kylelkh.springboot.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
