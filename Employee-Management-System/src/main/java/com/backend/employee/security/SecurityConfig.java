package com.backend.employee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class for security-related beans and settings.
 */
@Configuration
public class SecurityConfig {

 /**
  * Creates and configures a BCryptPasswordEncoder bean.
  *
  * @return A BCryptPasswordEncoder bean instance for password hashing and
  *         verification.
  */
 @Bean
 public  BCryptPasswordEncoder passwordEncoder() {
  return new BCryptPasswordEncoder();
 }
}
