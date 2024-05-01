package org.example.demo4.depservlets;

import org.example.demo4.repo.DepartmentRepoImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.example.demo4.repo.EmployeeRepo;
import org.example.demo4.repo.EmployeeRepoImpl;

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
