package com.oneteam.empsystem.servlets.empservlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.EmployeeRepo;
import com.oneteam.empsystem.repo.EmployeeRepoImpl;

import java.io.IOException;
import java.math.BigDecimal;

public class AddEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));
        BigDecimal salary = new BigDecimal(request.getParameter("salary"));

        Employee newEmployee = new Employee();
        newEmployee.setName(name);
        newEmployee.setEmail(email);
        Department department = new Department();
        department.setDepartmentId(departmentId);
        newEmployee.setDepartment(department);
        newEmployee.setSalary(salary);

        EmployeeRepo employeeRepo = new EmployeeRepoImpl();
        employeeRepo.save(newEmployee);

        response.sendRedirect(request.getContextPath() + "/employees"); // Redirect to employee list page
    }
}
