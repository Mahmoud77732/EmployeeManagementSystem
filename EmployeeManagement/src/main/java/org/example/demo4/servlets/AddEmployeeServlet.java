package org.example.demo4.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.example.demo4.entity.Employee;
import org.example.demo4.repo.EmployeeRepo;
import org.example.demo4.repo.EmployeeRepoImpl;

import java.io.IOException;

public class AddEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");

        Employee newEmployee = new Employee(name); // Assuming Employee constructor accepts name and position
        EmployeeRepo employeeRepo = new EmployeeRepoImpl();
        employeeRepo.save(newEmployee);

        response.sendRedirect(request.getContextPath() + "/employee-list"); // Redirect to employee list page
    }
}
