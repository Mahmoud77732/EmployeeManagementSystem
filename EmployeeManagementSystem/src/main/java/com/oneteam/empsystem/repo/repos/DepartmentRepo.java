package com.oneteam.empsystem.repo.repos;

import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.entity.Employee;

import java.util.List;

public interface DepartmentRepo extends GenericRepo<Department, Long> {

    List<Employee> getEmployees(Long departmentId);

}
