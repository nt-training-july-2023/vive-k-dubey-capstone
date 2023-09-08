package com.backend.employee.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.employee.dto.LoginDto;
import com.backend.employee.dto.RegisterDto;

/**
 * Service interface that defines methods related to user registration and
 * authentication.
 */
@Service
public interface RegisterService {

  /**
   * Adds a new administrator user based on the provided registration data.
   *
   * @param registerDto The data containing details for the new admin user.
   * @return A ResponseEntity with a message indicating the outcome of the
   *         registration process.
   */
  ResponseEntity<String> addAdmin(RegisterDto registerDto);

  /**
   * Authenticates a user's login credentials.
   *
   * @param loginDto The data containing the user's login credentials.
   * @return A ResponseEntity with a message indicating the success or failure
   *         of authentication.
   */
  ResponseEntity<String> authenticate(LoginDto loginDto);
}
