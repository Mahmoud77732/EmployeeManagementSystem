package com.oneteam.empsystem.service;

import com.oneteam.empsystem.entity.dto.DepartmentDTO;
import com.oneteam.empsystem.entity.mapper.DepartmentMapper;
import com.oneteam.empsystem.entity.pojo.Department;
import com.oneteam.empsystem.repo.DepartmentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> departments = departmentRepo.findAll();
        return departments.stream()
                .map(departmentMapper)
                .collect(Collectors.toList());
    }

    public DepartmentDTO getDepartmentById(Long id) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + id));
        return departmentMapper.apply(department);
    }

    public DepartmentDTO saveDepartment(Department department) {
        Department savedDepartment = departmentRepo.save(department);
        return departmentMapper.apply(savedDepartment);
    }

    public void deleteDepartment(Long id) {
        departmentRepo.deleteById(id);
    }
}
