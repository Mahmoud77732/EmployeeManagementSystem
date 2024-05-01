package com.oneteam.empsystem.servlets.empservlets;

import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.EmployeeRepo;
import com.oneteam.empsystem.repo.EmployeeRepoImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;

public class EditEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long employeeId = Long.parseLong(request.getParameter("id"));
        EmployeeRepo employeeRepo = new EmployeeRepoImpl();
        Employee employee = employeeRepo.findById(employeeId);
        request.setAttribute("employee", employee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EmployeePages/editEmployee.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long employeeId = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        BigDecimal salary = new BigDecimal(request.getParameter("salary"));

        EmployeeRepo employeeRepo = new EmployeeRepoImpl();
        Employee employee = employeeRepo.findById(employeeId);
        if (employee != null) {
            employee.setId(employeeId);
            employee.setName(name);
            employee.setEmail(email);
            if(!request.getParameter("departmentId").trim().isEmpty()){
                Long departmentId = Long.parseLong(request.getParameter("departmentId"));
                Department department = new Department();
                department.setDepartmentId(departmentId);
                employee.setDepartment(department);
            }
            else{
                employee.setDepartment(null);
            }
            employee.setSalary(salary);

            employeeRepo.update(employee);
        }


        response.sendRedirect(request.getContextPath() + "/employees"); // Redirect to employee list page
    }
}
