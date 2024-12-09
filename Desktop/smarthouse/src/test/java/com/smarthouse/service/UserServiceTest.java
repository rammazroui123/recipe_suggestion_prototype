package com.smarthouse.service;

import com.smarthouse.model.User;
import com.smarthouse.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUser_ShouldAddUser() {
        // Arrange
        User user = new User("john_doe", "john@example.com", "password123");
        when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = userService.addUser(user);

        // Assert
        assertNotNull(result);
        assertEquals("john_doe", result.getUsername());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getAllUsers_ShouldReturnListOfUsers() {
        // Arrange
        User user1 = new User("john_doe", "john@example.com", "password123");
        User user2 = new User("jane_doe", "jane@example.com", "password456");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        List<User> users = userService.getAllUsers();

        // Assert
        assertEquals(2, users.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById_ShouldReturnUser_WhenUserExists() {
        // Arrange
        User user = new User("john_doe", "john@example.com", "password123");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("john_doe", result.getUsername());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserById_ShouldThrowException_WhenUserDoesNotExist() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
        assertEquals("User not found with ID: 1", exception.getMessage());
        verify(userRepository, times(1)).findById(1L);
    }
}
