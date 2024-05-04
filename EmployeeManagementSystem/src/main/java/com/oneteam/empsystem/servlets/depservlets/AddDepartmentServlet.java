package com.oneteam.empsystem.servlets.depservlets;

import com.oneteam.empsystem.repo.repos.DepartmentRepo;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.reposimpl.DepartmentRepoImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addDepartment")
public class AddDepartmentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Department newDepartment = new Department();
        newDepartment.setName(name);
        newDepartment.setDescription(description);
        List<Employee> employees = new ArrayList<>();
        newDepartment.setEmployees(employees);
        DepartmentRepo departmentRepo = new DepartmentRepoImpl();
        departmentRepo.save(newDepartment);

        response.sendRedirect(request.getContextPath() + "/departments"); // Redirect to employee list page
    }

}
