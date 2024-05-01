package com.oneteam.empsystem.servlets.depservlets;

import com.oneteam.empsystem.repo.DepartmentRepoImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.oneteam.empsystem.repo.EmployeeRepo;
import com.oneteam.empsystem.repo.EmployeeRepoImpl;

import java.io.IOException;

public class DeleteDepartmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));
        DepartmentRepoImpl departmentRepo = new DepartmentRepoImpl();
        departmentRepo.remove(departmentId);

        response.sendRedirect(request.getContextPath() + "/departments"); // Redirect to employees list page
    }

}
