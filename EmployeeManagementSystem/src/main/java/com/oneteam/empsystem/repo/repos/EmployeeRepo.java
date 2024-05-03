package com.oneteam.empsystem.repo.repos;

import com.oneteam.empsystem.entity.Employee;

import java.util.List;

public interface EmployeeRepo extends GenericRepo<Employee, Long> {

    List<Employee> findByDepartmentId(Long departmentId);
    List<Employee> findByProjectId(Long projectId);

}
