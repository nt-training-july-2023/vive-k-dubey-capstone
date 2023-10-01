package com.backend.employee.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backend.employee.dto.CommonResponseDto;
import com.backend.employee.dto.RequestResourceDto;
import com.backend.employee.dto.RequestResourceManagerProjectDto;
import com.backend.employee.dto.RequestedDto;
import com.backend.employee.dto.RequestedOutDto;
import com.backend.employee.dto.ResourceRequestsAdminOutDto;
import com.backend.employee.entity.ProjectEntity;
import com.backend.employee.entity.RegisterEntity;
import com.backend.employee.entity.RequestResource;
import com.backend.employee.repo.ProjectRepo;
import com.backend.employee.repo.RegisterRepo;
import com.backend.employee.repo.RequestResourceRepo;
import com.backend.employee.service.RequestResourceService;

@ExtendWith(MockitoExtension.class)
public class RequestResourceServiceTest {

 @Mock
 private RegisterRepo registerRepository;

 @Mock
 private RequestResourceRepo requestResourceRepository;

 @InjectMocks
 private RequestResourceService requestResourceService;

 @Mock
 private ProjectRepo projectRepository;

 @Test
 public void testCreateRequestResource() {
  RequestResourceDto requestResourceDto = new RequestResourceDto();
  requestResourceDto.setEmpId("sampleEmpId");
  requestResourceDto.setManagerEmail("sampleManagerEmail");
  requestResourceDto.setProjectId(1L);
  requestResourceDto.setComment("Sample comment");

  RegisterEntity employee = new RegisterEntity();
  employee.setId(1L);
  RegisterEntity manager = new RegisterEntity();
  manager.setId(2L);

  when(registerRepository.findByEmpId("sampleEmpId"))
   .thenReturn(Optional.of(employee));
  when(registerRepository.findByEmpEmail("sampleManagerEmail"))
   .thenReturn(Optional.of(manager));

  requestResourceService.createRequestResource(requestResourceDto);

  verify(requestResourceRepository, times(1))
   .save(any(RequestResource.class));
 }

 @Test
 public void testIsRequested() {
  RequestedDto requestedDto = new RequestedDto();
  requestedDto.setEmpId("sampleEmpId");
  requestedDto.setManagerEmail("sampleManagerEmail");

  RegisterEntity employee = new RegisterEntity();
  employee.setId(1L);
  RegisterEntity manager = new RegisterEntity();
  manager.setId(2L);

  when(registerRepository.findByEmpId("sampleEmpId"))
   .thenReturn(Optional.of(employee));
  when(registerRepository.findByEmpEmail("sampleManagerEmail"))
   .thenReturn(Optional.of(manager));
  when(requestResourceRepository.findByEmployeeIdAndManagerId(1L, 2L))
   .thenReturn(new RequestResource());

  RequestedOutDto requestedOutDto = requestResourceService
   .isRequested(requestedDto);

  assertTrue(requestedOutDto.isRequested());
 }

 @Test
 public void testGetAllByManagerEmail() {
  String managerEmail = "sampleManagerEmail";

  RegisterEntity manager = new RegisterEntity();
  manager.setId(1L);
  when(registerRepository.findByEmpEmail(managerEmail))
   .thenReturn(Optional.of(manager));
  List<ProjectEntity> projectList = new ArrayList<>();
  ProjectEntity project1 = new ProjectEntity();
  project1.setProjectId(1L);
  project1.setName("Project 1");
  projectList.add(project1);

  ProjectEntity project2 = new ProjectEntity();
  project2.setProjectId(2L);
  project2.setName("Project 2");
  projectList.add(project2);

  when(projectRepository.findAllByManagerEmployeeId(manager.getId()))
   .thenReturn(projectList);

  List<RequestResourceManagerProjectDto> result = requestResourceService
   .getAllByManagerEmail(managerEmail);

  assertNotNull(result);
  assertEquals(2, result.size());

  assertEquals(1L, result.get(0).getId().longValue());
  assertEquals("Project 1", result.get(0).getProjectName());

  assertEquals(2L, result.get(1).getId().longValue());
  assertEquals("Project 2", result.get(1).getProjectName());
 }

 @Test
 public void testGetAllResourceRequests() {
  List<RequestResource> requestList = new ArrayList<>();
  RequestResource request1 = new RequestResource();
  request1.setId(1L);
  request1.setEmployeeId(1L);
  request1.setManagerId(2L);
  request1.setProjectId(3L);
  request1.setComment("Request 1 Comment");
  requestList.add(request1);

  RequestResource request2 = new RequestResource();
  request2.setId(2L);
  request2.setEmployeeId(4L);
  request2.setManagerId(5L);
  request2.setProjectId(6L);
  request2.setComment("Request 2 Comment");
  requestList.add(request2);

  when(requestResourceRepository.findAll()).thenReturn(requestList);

  RegisterEntity employee1 = new RegisterEntity();
  employee1.setId(1L);
  employee1.setEmpName("Employee 1");
  when(registerRepository.findById(1L)).thenReturn(Optional.of(employee1));

  RegisterEntity manager1 = new RegisterEntity();
  manager1.setId(2L);
  manager1.setEmpName("Manager 1");
  when(registerRepository.findById(2L)).thenReturn(Optional.of(manager1));

  ProjectEntity project1 = new ProjectEntity();
  project1.setProjectId(3L);
  project1.setName("Project 1");
  when(projectRepository.findByProjectId(3L)).thenReturn(project1);

  RegisterEntity employee2 = new RegisterEntity();
  employee2.setId(4L);
  employee2.setEmpName("Employee 2");
  when(registerRepository.findById(4L)).thenReturn(Optional.of(employee2));

  RegisterEntity manager2 = new RegisterEntity();
  manager2.setId(5L);
  manager2.setEmpName("Manager 2");
  when(registerRepository.findById(5L)).thenReturn(Optional.of(manager2));

  ProjectEntity project2 = new ProjectEntity();
  project2.setProjectId(6L);
  project2.setName("Project 2");
  when(projectRepository.findByProjectId(6L)).thenReturn(project2);

  List<ResourceRequestsAdminOutDto> result = requestResourceService
   .getAllResourceRequests();

  assertNotNull(result);
  assertEquals(2, result.size());

  assertEquals("Employee 1", result.get(0).getEmployeeName());
  assertEquals("Manager 1", result.get(0).getManagerName());
  assertEquals("Project 1", result.get(0).getProjectName());
  assertEquals("Request 1 Comment", result.get(0).getComment());
  assertEquals(1L, result.get(0).getId().longValue());
  assertEquals(1L, result.get(0).getEmployeeId().longValue());
  assertEquals(2L, result.get(0).getManagerId().longValue());
  assertEquals(3L, result.get(0).getProjectId().longValue());

  assertEquals("Employee 2", result.get(1).getEmployeeName());
  assertEquals("Manager 2", result.get(1).getManagerName());
  assertEquals("Project 2", result.get(1).getProjectName());
  assertEquals("Request 2 Comment", result.get(1).getComment());
  assertEquals(2L, result.get(1).getId().longValue());
  assertEquals(4L, result.get(1).getEmployeeId().longValue());
  assertEquals(5L, result.get(1).getManagerId().longValue());
  assertEquals(6L, result.get(1).getProjectId().longValue());
 }

 @Test
 public void testAcceptRequest() {
  RequestResource request = new RequestResource();
  request.setId(1L);
  request.setEmployeeId(2L);
  request.setManagerId(3L);
  request.setProjectId(4L);

  when(requestResourceRepository.findById(1L))
   .thenReturn(Optional.of(request));

  RegisterEntity employee = new RegisterEntity();
  employee.setId(2L);
  employee.setProjectId(null);

  when(registerRepository.findById(2L)).thenReturn(Optional.of(employee));

  CommonResponseDto response = requestResourceService.acceptRequest(1L);

  assertEquals("Request Accepted", response.getMessage());

  assertEquals(4L, employee.getProjectId().longValue());
  assertEquals(3L, employee.getManagerId().longValue());

  verify(requestResourceRepository, times(1)).deleteById(1L);
 }

 @Test
 public void testRejectResourceRequest() {
  doNothing().when(requestResourceRepository).deleteById(1L);

  requestResourceService.rejectResourceRequest(1L);

  verify(requestResourceRepository, times(1)).deleteById(1L);
 }
}
