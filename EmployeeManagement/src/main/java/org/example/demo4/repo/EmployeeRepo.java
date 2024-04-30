package org.example.demo4.repo;

import org.example.demo4.entity.Employee;

import java.util.List;

public interface EmployeeRepo {

    Employee findById(Long id);
    void save(Employee emp);
    void update(Employee emp);
    void remove(Employee emp);
    List<Employee> findAll();
    List<Employee> findAll2();

}
