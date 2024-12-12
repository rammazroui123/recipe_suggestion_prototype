package com.smarthouse.service;

import com.smarthouse.dto.UserDTO;
import com.smarthouse.model.User;
import com.smarthouse.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    // Get all users as UserDTOs
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getDietaryPreferences(),
                        user.getProfilePicture()
                ))
                .collect(Collectors.toList());
    }

    public User addUser(User user) {
        logger.info("Adding a new user: {}", user.getUsername());

        // Check if the username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            logger.error("Username already exists: {}", user.getUsername());
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }

        // Check if the email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            logger.error("Email already exists: {}", user.getEmail());
            throw new RuntimeException("Email already exists: " + user.getEmail());
        }

        // Save the user if validations pass
        return userRepository.save(user);
    }


    // Add the getUserById method
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getDietaryPreferences(),
                user.getProfilePicture()
        );
    }

    public User getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public List<User> getAllUserEntities() {
        return userRepository.findAll();
    }
}


