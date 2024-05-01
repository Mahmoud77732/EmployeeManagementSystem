package org.example.demo4.servlets.empservlets;

import org.example.demo4.repo.EmployeeRepo;
import org.example.demo4.repo.EmployeeRepoImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;

public class DeleteEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long employeeId = Long.parseLong(request.getParameter("id"));
        EmployeeRepo employeeRepo = new EmployeeRepoImpl();
        employeeRepo.remove(employeeId);

        response.sendRedirect(request.getContextPath() + "/employees"); // Redirect to employees list page
    }
}
