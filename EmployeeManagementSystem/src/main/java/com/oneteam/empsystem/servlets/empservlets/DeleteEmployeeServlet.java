package com.oneteam.empsystem.servlets.empservlets;

import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.repos.DepartmentRepo;
import com.oneteam.empsystem.repo.repos.EmployeeRepo;
import com.oneteam.empsystem.repo.reposimpl.DepartmentRepoImpl;
import com.oneteam.empsystem.repo.reposimpl.EmployeeRepoImpl;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long employeeId = Long.parseLong(request.getParameter("id"));
        EmployeeRepo employeeRepo = new EmployeeRepoImpl();
        DepartmentRepo departmentRepo = new DepartmentRepoImpl();
        Employee employee = employeeRepo.findById(employeeId);
        Department department = employee.getDepartment();
        department.getEmployees().remove(employee);
        departmentRepo.update(department);
        employeeRepo.remove(employee);

        response.sendRedirect(request.getContextPath() + "/employees"); // Redirect to employees list page
    }
}
