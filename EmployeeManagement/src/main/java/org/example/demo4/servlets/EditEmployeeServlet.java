package org.example.demo4.servlets;

import org.example.demo4.entity.Employee;
import org.example.demo4.repo.EmployeeRepo;
import org.example.demo4.repo.EmployeeRepoImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

public class EditEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long employeeId = Long.parseLong(request.getParameter("id"));
        EmployeeRepo employeeRepo = new EmployeeRepoImpl();
        Employee employee = employeeRepo.findById(employeeId);
        request.setAttribute("employee", employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/edit-employee.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long employeeId = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(employeeId);
        EmployeeRepo employeeRepo = new EmployeeRepoImpl();
        employeeRepo.update(updatedEmployee);

        response.sendRedirect(request.getContextPath() + "/employee-list"); // Redirect to employee list page
    }
}
