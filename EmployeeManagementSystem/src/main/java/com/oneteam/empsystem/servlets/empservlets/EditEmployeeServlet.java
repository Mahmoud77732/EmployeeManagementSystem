package com.oneteam.empsystem.servlets.empservlets;

import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.repos.DepartmentRepo;
import com.oneteam.empsystem.repo.repos.EmployeeRepo;
import com.oneteam.empsystem.repo.reposimpl.DepartmentRepoImpl;
import com.oneteam.empsystem.repo.reposimpl.EmployeeRepoImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.oneteam.empsystem.db.HibernateConnectionmanager.*;

public class EditEmployeeServlet extends HttpServlet {

    private final EmployeeRepo employeeRepo;
    private final DepartmentRepo departmentRepo;

    public EditEmployeeServlet() {
        this.employeeRepo = new EmployeeRepoImpl();
        this.departmentRepo = new DepartmentRepoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long employeeId = Long.parseLong(request.getParameter("id"));
        request.setAttribute("employee", employeeRepo.findById(employeeId));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/EmployeePages/editEmployee.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long employeeId = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        BigDecimal salary = new BigDecimal(request.getParameter("salary"));
        String departmentName = request.getParameter("departmentName");

        Employee employee = employeeRepo.findById(employeeId);
        if (employee != null) {
            employee.setId(employeeId);
            employee.setName(name);
            employee.setEmail(email);
            employee.setSalary(salary);
            Department department = departmentRepo.findDepartmentByName(departmentName);
            employee.setDepartment(department);
            department.getEmployees().add(employee);
            departmentRepo.update(department);
            employeeRepo.update(employee);
        }


        response.sendRedirect(request.getContextPath() + "/employees"); // Redirect to employee list page
    }
}
