package com.oneteam.empsystem.servlets.auth;

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
import java.math.BigDecimal;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("pages/AuthPages/registerForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User(username, "Employee", password); // Employee by default

        UserRepo userRepo = new UserRepoImpl();
        userRepo.save(user);
        EmployeeRepo employeeRepo = new EmployeeRepoImpl();
        Employee employee = new Employee(username, email, BigDecimal.valueOf(0));
        employeeRepo.save(employee);

        // response.sendRedirect("pages/AuthPages/login.jsp");
        request.getRequestDispatcher("pages/AuthPages/login.jsp").forward(request, response);
    }
}
