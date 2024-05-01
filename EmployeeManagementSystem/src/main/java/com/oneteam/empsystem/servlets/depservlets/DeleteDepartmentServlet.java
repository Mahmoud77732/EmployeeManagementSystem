package com.oneteam.empsystem.servlets.depservlets;

import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.DepartmentRepoImpl;

import com.oneteam.empsystem.repo.EmployeeRepoImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

public class DeleteDepartmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));
        DepartmentRepoImpl departmentRepo = new DepartmentRepoImpl();
        EmployeeRepoImpl employeeRepo = new EmployeeRepoImpl();
        List<Employee> employees =  employeeRepo.findByDepartmentId(departmentId);
        for(Employee emp : employees){
            emp.setDepartment(null);
            employeeRepo.update(emp);
        }
        Department  department = departmentRepo.findById(departmentId);
        department.setEmployees(null);
        departmentRepo.update(department);
        departmentRepo.remove(departmentId);

        response.sendRedirect(request.getContextPath() + "/departments"); // Redirect to employees list page
    }

}
