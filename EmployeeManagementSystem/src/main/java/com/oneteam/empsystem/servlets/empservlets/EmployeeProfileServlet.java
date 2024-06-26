package com.oneteam.empsystem.servlets.empservlets;

import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.entity.User;
import com.oneteam.empsystem.repo.repos.EmployeeRepo;
import com.oneteam.empsystem.repo.repos.UserRepo;
import com.oneteam.empsystem.repo.reposimpl.EmployeeRepoImpl;
import com.oneteam.empsystem.repo.reposimpl.UserRepoImpl;
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
        UserRepo userRepo = new UserRepoImpl();
        Employee employee = employeeRepo.getEmployeeByUsername(username);
        User user = userRepo.findByUsername(employee.getName());

        // Set attributes to be accessed in JSP
        request.setAttribute("employee", employee);
        request.setAttribute("projects", employee.getProjects());
        request.setAttribute("salary", employee.getSalary());
        request.setAttribute("user", user);

        // Forward to employee profile JSP
        request.getRequestDispatcher("/pages/AuthPages/employeeProfile.jsp").forward(request, response);
    }

}
