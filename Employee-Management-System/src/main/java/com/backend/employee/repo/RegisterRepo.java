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

 /**
  * Retrieves an employee entity by its employee role.
  *
  * @param empRole The employee role to search for.
  * @return The employee entity with the specified role.
  */
 RegisterEntity findByEmpRole(String empRole);

 /**
  * Retrieves a list of employee entities with a specific employee role.
  *
  * @param empRole The employee role to filter by.
  * @return A list of employee entities with the specified role.
  */
 List<RegisterEntity> findAllByEmpRole(String empRole);

 /**
  * Finds all entities by role which is not admin.
  *
  * @param empRole The role to search for.
  * @return A list of RegisterEntity objects that match the given role and are
  *         not admin.
  */
 List<RegisterEntity> findAllByEmpRoleNot(String empRole);

 /**
  * Finds all entities by projectId.
  *
  * @param projectId projectId.
  * @return A list.
  */
 List<RegisterEntity> findAllByProjectId(Long projectId);
}
