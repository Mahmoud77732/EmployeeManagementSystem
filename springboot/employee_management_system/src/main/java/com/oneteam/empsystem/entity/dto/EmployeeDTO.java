package com.oneteam.empsystem.entity.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;
    private String name;
    private BigDecimal salary;
    private String email;
    private DepartmentDTO department;
    private Set<ProjectDTO> projects;

}
