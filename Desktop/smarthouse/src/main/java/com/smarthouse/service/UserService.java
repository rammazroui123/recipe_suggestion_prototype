package com.smarthouse.service;

import com.smarthouse.model.User;
import com.smarthouse.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        logger.info("Fetching all users from the database");
        return userRepository.findAll();
    }

    public User addUser(User user) {
        logger.info("Adding a new user: {}", user.getUsername());
        return userRepository.save(user);
    }
}

