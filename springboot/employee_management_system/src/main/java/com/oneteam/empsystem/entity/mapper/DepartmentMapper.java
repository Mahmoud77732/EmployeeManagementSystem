package com.oneteam.empsystem.entity.mapper;

import com.oneteam.empsystem.entity.dto.DepartmentDTO;
import com.oneteam.empsystem.entity.pojo.Department;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DepartmentMapper implements Function<Department, DepartmentDTO> {

    @Override
    public DepartmentDTO apply(Department department) {
        DepartmentDTO departmentDTO = new DepartmentDTO(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
        return departmentDTO;
    }

}
