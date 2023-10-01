package com.backend.employee.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class ResourceRequestsAdminOutDtoTest {

    @Test
    public void testGetSetId() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        Long id = 123L;
        dto.setId(id);

        assertEquals(id, dto.getId());
    }

    @Test
    public void testGetSetEmployeeId() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        Long employeeId = 456L;
        dto.setEmployeeId(employeeId);

        assertEquals(employeeId, dto.getEmployeeId());
    }

    @Test
    public void testGetSetEmployeeName() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        String employeeName = "Vivek Dubey";
        dto.setEmployeeName(employeeName);

        assertEquals(employeeName, dto.getEmployeeName());
    }

    @Test
    public void testGetSetManagerName() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        String managerName = "Abhay Gupta";
        dto.setManagerName(managerName);

        assertEquals(managerName, dto.getManagerName());
    }

    @Test
    public void testGetSetProjectName() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        String projectName = "Project XYZ";
        dto.setProjectName(projectName);

        assertEquals(projectName, dto.getProjectName());
    }

    @Test
    public void testGetSetComment() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        String comment = "Request for additional resources.";
        dto.setComment(comment);

        assertEquals(comment, dto.getComment());
    }

    @Test
    public void testGetSetManagerId() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        Long managerId = 789L;
        dto.setManagerId(managerId);

        assertEquals(managerId, dto.getManagerId());
    }

    @Test
    public void testGetSetProjectId() {
        ResourceRequestsAdminOutDto dto = new ResourceRequestsAdminOutDto();
        Long projectId = 101L;
        dto.setProjectId(projectId);

        assertEquals(projectId, dto.getProjectId());
    }
    
    private ResourceRequestsAdminOutDto dto1;
    private ResourceRequestsAdminOutDto dto2;

    @BeforeEach
    public void setUp() {
        dto1 = new ResourceRequestsAdminOutDto();
        dto1.setId(1L);
        dto1.setEmployeeId(100L);
        dto1.setEmployeeName("Vivek Dubey");
        dto1.setManagerName("Manager A");
        dto1.setProjectName("Project X");
        dto1.setComment("Request Comment");
        dto1.setManagerId(200L);
        dto1.setProjectId(300L);

        dto2 = new ResourceRequestsAdminOutDto();
        dto2.setId(1L);
        dto2.setEmployeeId(100L);
        dto2.setEmployeeName("Vivek Dubey");
        dto2.setManagerName("Manager A");
        dto2.setProjectName("Project X");
        dto2.setComment("Request Comment");
        dto2.setManagerId(200L);
        dto2.setProjectId(300L);
    }

    @Test
    public void testHashCode() {
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    public void testEquals() {
        assertEquals(dto1, dto2);
    }

    @Test
    public void testToString() {
        String expectedString = "ResourceRequestsAdminOutDto [id=1, employeeId=100, employeeName=Vivek Dubey, managerName=Manager A, projectName=Project X, comment=Request Comment, managerId=200, projectId=300]";
        assertEquals(expectedString, dto1.toString());
    }
}
