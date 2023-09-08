package com.backend.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerInfoDto {
    
    private Long id;
    private String managerName;
    private String managerEmployeeId;
}