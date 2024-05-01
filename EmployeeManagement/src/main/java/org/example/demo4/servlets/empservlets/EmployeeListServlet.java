package org.example.demo4.servlets.empservlets;

import org.example.demo4.entity.Employee;
import org.example.demo4.repo.EmployeeRepo;
import org.example.demo4.repo.EmployeeRepoImpl;

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
