package com.oneteam.empsystem.service;

import com.oneteam.empsystem.entity.pojo.Employee;
import com.oneteam.empsystem.entity.pojo.Project;
import com.oneteam.empsystem.repo.EmployeeRepo;
import com.oneteam.empsystem.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private ProjectRepo projectRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    public Employee assignProject(Long empId, Long projectId) throws NotFoundException {
        Employee updatedEmployee = employeeRepo.findById(empId).orElseThrow(() -> new NotFoundException());
        Project project = projectRepo.findById(projectId).orElseThrow(() -> new NotFoundException());
        // Assign project to employee
        updatedEmployee.getProjects().add(project);
        employeeRepo.save(updatedEmployee);
        project.getEmployees().add(updatedEmployee);
        projectRepo.save(project);

        return updatedEmployee;
    }
}
