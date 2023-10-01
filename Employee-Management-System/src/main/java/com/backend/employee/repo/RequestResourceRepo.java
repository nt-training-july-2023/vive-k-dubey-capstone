package com.backend.employee.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.employee.entity.RequestResource;

/**
 * Represents repository for request resource.
 */
@Repository
public interface RequestResourceRepo
        extends JpaRepository<RequestResource, Long> {
    /**
     * Repository method to find reqsource request.
     *
     * @param employeeId
     * @param managerId
     * @param projectId
     * @return RequestResource.
     */
    RequestResource findByEmployeeIdAndManagerId(Long employeeId,
            Long managerId);

    void deleteByEmployeeId(Long employeeId);
    
    List<RequestResource> findByEmployeeId(Long id);
}
