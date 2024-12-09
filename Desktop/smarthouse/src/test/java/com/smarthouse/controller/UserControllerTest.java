package com.smarthouse.controller;

import com.smarthouse.model.User;
import com.smarthouse.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        System.out.println("Clearing database before test");
        userRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        System.out.println("Clearing database after test");
        userRepository.deleteAll();
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() throws Exception {
        // Arrange
        userRepository.save(new User("unique_john_doe", "john_unique@example.com", "password123"));
        userRepository.save(new User("unique_jane_doe", "jane_unique@example.com", "password456"));

        // Act & Assert
        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].username").value("unique_john_doe"))
                .andExpect(jsonPath("$[1].username").value("unique_jane_doe"));
    }

    @Test
    void addUser_ShouldCreateNewUser() throws Exception {
        // Act & Assert
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"new_user\", \"email\":\"new_user@example.com\", \"password\":\"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("new_user"));
    }
}
