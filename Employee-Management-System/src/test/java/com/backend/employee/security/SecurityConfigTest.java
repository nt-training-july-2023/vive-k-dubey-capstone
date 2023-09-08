package com.backend.employee.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class SecurityConfigTest {

    @Test
    public void testPasswordEncoderNotNull() {
        // Arrange
        SecurityConfig securityConfig = new SecurityConfig();

        // Act
        BCryptPasswordEncoder passwordEncoder = securityConfig.passwordEncoder(); 

        // Assert
        assertNotNull(passwordEncoder);
    }

    @Test
    public void testPasswordEncoderConsistency() {
        // Arrange
        SecurityConfig securityConfig = new SecurityConfig();
        BCryptPasswordEncoder passwordEncoder = securityConfig.passwordEncoder();

        String rawPassword = "mySecretPassword";

        // Act
        String encodedPassword1 = passwordEncoder.encode(rawPassword);
        String encodedPassword2 = passwordEncoder.encode(rawPassword);

        // Assert
        assertNotEquals(encodedPassword1, encodedPassword2);
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword1));
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword2));
    }
}
