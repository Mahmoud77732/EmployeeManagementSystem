package com.oneteam.empsystem.service;

import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

    public List<Department> getAllDepartments() {
        return departmentRepo.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepo.findById(id);
    }

    public Department saveDepartment(Department department) {
        return departmentRepo.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepo.deleteById(id);
    }
}
