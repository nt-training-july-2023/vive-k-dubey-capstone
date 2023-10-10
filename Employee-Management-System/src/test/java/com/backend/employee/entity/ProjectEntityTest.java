package com.backend.employee.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.backend.employee.dto.ProjectDto;
import com.backend.employee.entity.ProjectEntity;

public class ProjectEntityTest {

    @Test
    void testEqualsSameObject() {
        ProjectEntity projectEntity = new ProjectEntity();
        assertTrue(projectEntity.equals(projectEntity));
    }

    @Test
    void testEqualsNotEqualObjects() {
        ProjectEntity projectEntity1 = new ProjectEntity();
        projectEntity1.setProjectId(1L);
        projectEntity1.setName("Project1");

        ProjectDto projectDto = new ProjectDto();
        projectDto.setProjectId(2L); 
        projectDto.setName("Project2"); 
 
        ProjectEntity projectEntity2 = new ProjectEntity(projectDto);

        assertFalse(projectEntity1.equals(projectEntity2));
    }

    @Test
    void testEqualsDifferentClasses() {
        ProjectEntity projectEntity = new ProjectEntity();
        Object otherObject = new Object(); 

        assertFalse(projectEntity.equals(otherObject));
    }

    @Test
    void testEqualsNullObject() {
        ProjectEntity projectEntity = new ProjectEntity();
        assertFalse(projectEntity.equals(null));
    }
}
