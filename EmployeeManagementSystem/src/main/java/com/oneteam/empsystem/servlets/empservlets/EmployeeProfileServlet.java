package com.oneteam.empsystem.servlets.empservlets;

import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.repos.EmployeeRepo;
import com.oneteam.empsystem.repo.reposimpl.EmployeeRepoImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/empProfile")
public class EmployeeProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get authenticated employee's username from session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/pages/AuthPages/login.jsp");
            return;
        }
        String username = (String) session.getAttribute("username");

        EmployeeRepo employeeRepo = new EmployeeRepoImpl();
        Employee employee = employeeRepo.getEmployeeByUsername(username);

        // Set attributes to be accessed in JSP
        request.setAttribute("employee", employee);
        request.setAttribute("projects", employee.getProjects());
        request.setAttribute("salary", employee.getSalary());

        // Forward to employee profile JSP
        request.getRequestDispatcher("/pages/AuthPages/employeeProfile.jsp").forward(request, response);
    }

}
