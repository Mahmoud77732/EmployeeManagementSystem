package com.oneteam.empsystem.service;

import com.oneteam.empsystem.entity.dto.EmployeeDTO;
import com.oneteam.empsystem.entity.mapper.EmployeeDTOMapper;
import com.oneteam.empsystem.entity.pojo.Employee;
import com.oneteam.empsystem.entity.pojo.Project;
import com.oneteam.empsystem.repo.repo_interface.EmployeeRepo;
import com.oneteam.empsystem.repo.repo_interface.ProjectRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private EmployeeDTOMapper employeeDTOMapper;


    //TODO using DTO
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        return employees.stream()
                .map(employeeDTOMapper)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        return employeeDTOMapper.apply(employee);
    }

    public EmployeeDTO saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepo.save(employee);
        return employeeDTOMapper.apply(savedEmployee);
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    public EmployeeDTO assignProject(Long empId, Long projectId) throws NotFoundException {
        Employee updatedEmployee = employeeRepo.findById(empId).orElseThrow(() -> new NotFoundException());
        Project project = projectRepo.findById(projectId).orElseThrow(() -> new NotFoundException());
        // Assign project to employee
        updatedEmployee.getProjects().add(project);
        employeeRepo.save(updatedEmployee);
        project.getEmployees().add(updatedEmployee);
        projectRepo.save(project);

        return employeeDTOMapper.apply(updatedEmployee);
    }

    ///////////////////////////////////////////////////////////////////////////

    /* TODO without DTO
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
    */

}
