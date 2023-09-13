package com.backend.employee.repo;

import com.backend.employee.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The repository interface for managing project entities.
 */
@Repository
public interface ProjectRepo extends JpaRepository<ProjectEntity, Long> {

	/**
	 * Retrieves a list of all projects.
	 *
	 * @return A list of all project entities.
	 */
	List<ProjectEntity> findAll();

	/**
	 * Retrieves a project entity by its unique identifier.
	 *
	 * @param projectId The unique identifier of the project.
	 * @return The project entity corresponding to the given identifier.
	 */
	ProjectEntity findByProjectId(Long projectId);

	/**
	 * Retrieves a list of project entities managed by a specific manager.
	 *
	 * @param managerId The unique identifier of the manager.
	 * @return A list of project entities managed by the specified manager.
	 */
	List<ProjectEntity> findAllByManagerEmployeeId(Long managerId);

	/**
	 * Retrieves a project entity by its name.
	 *
	 * @param name The name of the project.
	 * @return The project entity with the specified name.
	 */
	ProjectEntity findByName(String name);
}
