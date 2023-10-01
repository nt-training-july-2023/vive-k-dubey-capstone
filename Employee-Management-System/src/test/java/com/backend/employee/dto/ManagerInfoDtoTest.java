package com.backend.employee.dto;

import com.backend.employee.dto.ManagerInfoDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerInfoDtoTest {

 @Test
 public void testGetterSetter() {
     ManagerInfoDto managerInfoDto = new ManagerInfoDto();

     managerInfoDto.setId(1L);
     managerInfoDto.setManagerName("Rajesh Kumar");
     managerInfoDto.setManagerEmployeeId("EMP002");

     assertEquals(1L, managerInfoDto.getId());
     assertEquals("Rajesh Kumar", managerInfoDto.getManagerName());
     assertEquals("EMP002", managerInfoDto.getManagerEmployeeId());
 }

 @Test
 public void testHashCode() {
     ManagerInfoDto managerInfoDto1 = new ManagerInfoDto(1L, "Vivek Dubey", "EMP001");
     ManagerInfoDto managerInfoDto2 = new ManagerInfoDto(1L, "Vivek Dubey", "EMP001");

     assertEquals(managerInfoDto1.hashCode(), managerInfoDto2.hashCode());
 }

 @Test
 public void testEquals() {
     ManagerInfoDto managerInfoDto1 = new ManagerInfoDto(1L, "Vivek Dubey", "EMP001");
     ManagerInfoDto managerInfoDto2 = new ManagerInfoDto(1L, "Vivek Dubey", "EMP001");
     ManagerInfoDto managerInfoDto3 = new ManagerInfoDto(2L, "Ashish", "EMP002");

     assertTrue(managerInfoDto1.equals(managerInfoDto2));
     assertFalse(managerInfoDto1.equals(managerInfoDto3));
 }

 @Test
 public void testToString() {
     ManagerInfoDto managerInfoDto = new ManagerInfoDto(1L, "Vivek Dubey", "EMP001");

     String expectedString = "ManagerInfoDto [id=1, managerName=Vivek Dubey, managerEmployeeId=EMP001]";
     assertEquals(expectedString, managerInfoDto.toString());
 }
}
