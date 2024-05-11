package com.oneteam.empsystem.entity.dto;

import java.math.BigDecimal;
import java.util.Set;


public record EmployeeDTO (
    Long id,
    String name,
    BigDecimal salary,
    String email,
    DepartmentDTO department,
    Set<ProjectDTO> projects
){

}
