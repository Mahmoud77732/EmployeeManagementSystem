package com.oneteam.empsystem.entity.mapper;

import com.oneteam.empsystem.entity.dto.DepartmentDTO;
import com.oneteam.empsystem.entity.dto.EmployeeDTO;
import com.oneteam.empsystem.entity.dto.ProjectDTO;
import com.oneteam.empsystem.entity.pojo.Employee;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class EmployeeDTOMapper implements Function<Employee, EmployeeDTO> {

    @Override
    public EmployeeDTO apply(Employee employee) {
        // Map DepartmentDTO
        DepartmentDTO departmentDTO = null;
        if (employee.getDepartment() != null) {
            departmentDTO = new DepartmentDTO(
                    employee.getDepartment().getId(),
                    employee.getDepartment().getName(),
                    employee.getDepartment().getDescription()
            );
        }
        EmployeeDTO employeeDTO = new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                employee.getSalary(),
                employee.getEmail(),
                departmentDTO,
                // Map ProjectDTOs
                employee.getProjects().stream()
                        .map(project -> {
                            return new ProjectDTO(
                                    project.getId(),
                                    project.getName(),
                                    project.getDescription()
                            );
                        })
                        .collect(Collectors.toSet())
        );
        return employeeDTO;
    }
}
