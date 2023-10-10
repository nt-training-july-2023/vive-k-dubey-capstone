package com.backend.employee.entity;

import com.backend.employee.entity.RequestResource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RequestResourceTest {

    @Test
    public void testGetSetId() {
        RequestResource requestResource = new RequestResource();
        Long id = 123L;
        requestResource.setId(id);

        assertEquals(id, requestResource.getId());
    }

    @Test
    public void testGetSetEmployeeId() {
        RequestResource requestResource = new RequestResource();
        Long employeeId = 456L;
        requestResource.setEmployeeId(employeeId);

        assertEquals(employeeId, requestResource.getEmployeeId());
    }

    @Test
    public void testGetSetManagerId() {
        RequestResource requestResource = new RequestResource();
        Long managerId = 789L;
        requestResource.setManagerId(managerId);

        assertEquals(managerId, requestResource.getManagerId());
    }

    @Test
    public void testGetSetProjectId() {
        RequestResource requestResource = new RequestResource();
        Long projectId = 101L;
        requestResource.setProjectId(projectId);

        assertEquals(projectId, requestResource.getProjectId());
    }

    @Test
    public void testGetSetComment() {
        RequestResource requestResource = new RequestResource();
        String comment = "Request for additional resources.";
        requestResource.setComment(comment);

        assertEquals(comment, requestResource.getComment());
    }
}

