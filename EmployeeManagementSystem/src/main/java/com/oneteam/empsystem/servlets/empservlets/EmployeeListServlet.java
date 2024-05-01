package com.oneteam.empsystem.servlets.empservlets;

import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.EmployeeRepo;
import com.oneteam.empsystem.repo.EmployeeRepoImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

public class EmployeeListServlet extends HttpServlet {
    private final EmployeeRepo employeeRepo;

    public EmployeeListServlet() {
        this.employeeRepo = new EmployeeRepoImpl(); // Initialize your DAO here
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees =  employeeRepo.findAll();
        if(!employees.isEmpty()){
            request.setAttribute("employees", employees);
            System.out.println("======>" + employees.get(0).getName());
            request.getRequestDispatcher("/pages/EmployeePages/employee-list.jsp").forward(request, response);
        }
        else{
            request.setAttribute("employees", null);
            request.getRequestDispatcher("/pages/EmployeePages/employee-list.jsp").forward(request, response);
        }
    }

}
