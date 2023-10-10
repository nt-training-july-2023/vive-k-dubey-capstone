package com.backend.employee.dto;

import com.backend.employee.dto.CommonResponseDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommonResponseDtoTest {

 @Test
 public void testMessageGetterSetter() {
  CommonResponseDto responseDto = new CommonResponseDto();

  responseDto.setMessage("Test Message");

  assertEquals("Test Message", responseDto.getMessage());
 }

 @Test
 public void testNoArgsConstructor() {
  CommonResponseDto responseDto = new CommonResponseDto();

  assertEquals(null, responseDto.getMessage());

  responseDto.setMessage("Test Message");

  assertEquals("Test Message", responseDto.getMessage());
 }

 @Test
 public void testAllArgsConstructor() {
  CommonResponseDto responseDto = new CommonResponseDto("Test Message");

  assertEquals("Test Message", responseDto.getMessage());
 }

 @Test
 public void testHashCode() {
  CommonResponseDto dto1 = new CommonResponseDto("Test Message");
  CommonResponseDto dto2 = new CommonResponseDto("Test Message");

  assertEquals(dto1.hashCode(), dto2.hashCode());
 }

 @Test
 public void testHashCodeNot() {
  CommonResponseDto dto1 = new CommonResponseDto("Test Message");
  CommonResponseDto dto2 = new CommonResponseDto(" Message");

  assertNotEquals(dto1.hashCode(), dto2.hashCode());
 }

 @Test
 public void testEquals() {
  CommonResponseDto dto1 = new CommonResponseDto("Test Message");
  CommonResponseDto dto2 = new CommonResponseDto("Test Message");

  assertTrue(dto1.equals(dto2));
 }

 @Test
 public void testEqualsNot() {
  CommonResponseDto dto1 = new CommonResponseDto(" Message");
  CommonResponseDto dto2 = new CommonResponseDto("Test Message");

  assertFalse(dto1.equals(dto2));
 }

 @Test
 public void testToString() {
  CommonResponseDto responseDto = new CommonResponseDto("Test Message");

  assertEquals("CommonResponseDto [message=Test Message]",
   responseDto.toString());
 }
}
