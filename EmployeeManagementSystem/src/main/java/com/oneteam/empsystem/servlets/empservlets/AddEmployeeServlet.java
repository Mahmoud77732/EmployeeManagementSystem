package com.oneteam.empsystem.servlets.empservlets;

import com.oneteam.empsystem.repo.repos.DepartmentRepo;
import com.oneteam.empsystem.repo.repos.EmployeeRepo;
import com.oneteam.empsystem.repo.reposimpl.DepartmentRepoImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.reposimpl.EmployeeRepoImpl;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {

    private final EmployeeRepo employeeRepo = new EmployeeRepoImpl();
    private final DepartmentRepo departmentRepo = new DepartmentRepoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        BigDecimal salary = new BigDecimal(request.getParameter("salary"));
        Employee newEmployee = new Employee();
        newEmployee.setName(name);
        newEmployee.setEmail(email);
        newEmployee.setSalary(salary);
        if(!request.getParameter("departmentName").trim().isEmpty()){
            String departmentName = (request.getParameter("departmentName"));
            Department department = departmentRepo.findDepartmentByName(departmentName);
            department.setName(departmentName);
            newEmployee.setDepartment(department);
            department.getEmployees().add(newEmployee);
            departmentRepo.update(department);
        }
        else{
            newEmployee.setDepartment(null);
        }
        employeeRepo.save(newEmployee);
        response.sendRedirect(request.getContextPath() + "/employees"); // Redirect to employee list page
    }
}
