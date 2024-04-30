package org.example.demo4.service;

import org.example.demo4.entity.Employee;
import org.example.demo4.repo.EmployeeRepo;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepo employeeRepo;

    public Employee findById(Long id) {
        return employeeRepo.findById(id);
    }

    public void save(Employee emp) {
        employeeRepo.save(emp);
    }

    public void updateUser(Employee emp) {
        employeeRepo.update(emp);
    }

    public void removeEmployee(Employee emp) {
        employeeRepo.remove(emp);
    }

    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

}
