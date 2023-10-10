package com.backend.employee.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class SecurityConfigTest {

    @Test
    public void testPasswordEncoderNotNull() {
        SecurityConfig securityConfig = new SecurityConfig();

        BCryptPasswordEncoder passwordEncoder = securityConfig.passwordEncoder(); 

        assertNotNull(passwordEncoder);
    }

    @Test
    public void testPasswordEncoderConsistency() {
        SecurityConfig securityConfig = new SecurityConfig();
        BCryptPasswordEncoder passwordEncoder = securityConfig.passwordEncoder();

        String rawPassword = "mySecretPassword";

        String encodedPassword1 = passwordEncoder.encode(rawPassword);
        String encodedPassword2 = passwordEncoder.encode(rawPassword);
        
        assertNotEquals(encodedPassword1, encodedPassword2);
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword1));
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword2));
    }
}
