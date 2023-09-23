package com.backend.employee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents request resource.
 */
@Entity
@Table(name = "request_resource")
public class RequestResource {
 /**
  * Id of the request resource table.
  */
 @Id
 @GeneratedValue(strategy = GenerationType.SEQUENCE)
 private Long id;
 /**
  * Employee id of request resource.
  */
 @Column(nullable = false)
 private Long employeeId;
 /**
  * Manger id of request resource.
  */
 @Column(nullable = false)
 private Long managerId;
 /**
  * Project id of request resource.
  */
 @Column(nullable = false)
 private Long projectId;
 /**
  * Comment of request resource.
  */
 @Column(columnDefinition = "TEXT", nullable = false)
 private String comment;

 /**
  * @return the id
  */
 public Long getId() {
  return id;
 }

 /**
  * @param idParam the id to set
  */
 public void setId(Long idParam) {
  this.id = idParam;
 }

 /**
  * @return the employeeId
  */
 public Long getEmployeeId() {
  return employeeId;
 }

 /**
  * @param long1 the employeeId to set
  */
 public void setEmployeeId(Long long1) {
  this.employeeId = long1;
 }

 /**
  * @return the managerId
  */
 public Long getManagerId() {
  return managerId;
 }

 /**
  * @param managerIdParam the managerId to set
  */
 public void setManagerId(Long managerIdParam) {
  this.managerId = managerIdParam;
 }

 /**
  * @return the projectId
  */
 public Long getProjectId() {
  return projectId;
 }

 /**
  * @param projectIdParam the projectId to set
  */
 public void setProjectId(Long projectIdParam) {
  this.projectId = projectIdParam;
 }

 /**
  * @return the comment
  */
 public String getComment() {
  return comment;
 }

 /**
  * @param commentParam the comment to set
  */
 public void setComment(String commentParam) {
  this.comment = commentParam;
 }
}