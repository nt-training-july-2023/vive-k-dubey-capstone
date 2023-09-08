package com.backend.employee.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.employee.entity.RegisterEntity;

/**
 * Repository interface for accessing and managing RegisterEntity objects in the
 * database.
 */
@Repository
public interface RegisterRepo extends JpaRepository<RegisterEntity, Long> {

  /**
   * Retrieves a RegisterEntity by its email.
   *
   * @param email The email to search for.
   * @return An Optional containing the RegisterEntity if found, or an empty
   *         Optional if not found.
   */
  Optional<RegisterEntity> findByEmpEmail(String email);

  /**
   * Retrieves a RegisterEntity by its contact number.
   *
   * @param contactNo The contact number to search for.
   * @return An Optional containing the RegisterEntity if found, or an empty
   *         Optional if not found.
   */
  Optional<RegisterEntity> findByEmpContactNo(String contactNo);

  /**
   * Retrieves a RegisterEntity by its employee ID.
   *
   * @param empId The employee ID to search for.
   * @return An Optional containing the RegisterEntity if found, or an empty
   *         Optional if not found.
   */
  Optional<RegisterEntity> findByEmpId(String empId);

  RegisterEntity findByEmpRole(String string);
  List<RegisterEntity> findAllByEmpRole(String string);
}
