package com.oneteam.empsystem.repo;

import com.oneteam.empsystem.entity.Employee;

import java.util.List;

public interface EmployeeRepo {

    Employee findById(Long id);
    void save(Employee emp);
    void update(Employee emp);
    void remove(Long id);
    List<Employee> findAll();
    List<Employee> findAll2();

}
