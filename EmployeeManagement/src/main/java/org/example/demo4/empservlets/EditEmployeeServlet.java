package org.example.demo4.empservlets;

import org.example.demo4.entity.Department;
import org.example.demo4.entity.Employee;
import org.example.demo4.repo.EmployeeRepo;
import org.example.demo4.repo.EmployeeRepoImpl;

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
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));
        BigDecimal salary = new BigDecimal(request.getParameter("salary"));

        EmployeeRepo employeeRepo = new EmployeeRepoImpl();
        Employee employee = employeeRepo.findById(employeeId);
        if (employee != null) {
            System.out.println("=1===> " + employee.getId());
            employee.setId(employeeId);
            employee.setName(name);
            employee.setEmail(email);
            Department department = new Department();
            department.setDepartmentId(departmentId);
            employee.setDepartment(department);
            employee.setSalary(salary);

            employeeRepo.update(employee);
        }

        System.out.println("=2===> " + employee.getId());

        response.sendRedirect(request.getContextPath() + "/employees"); // Redirect to employee list page
    }
}
