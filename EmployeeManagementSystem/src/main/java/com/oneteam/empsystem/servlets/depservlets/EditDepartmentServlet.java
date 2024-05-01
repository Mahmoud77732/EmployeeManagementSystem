package com.oneteam.empsystem.servlets.depservlets;

import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.DepartmentRepoImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.oneteam.empsystem.repo.EmployeeRepo;
import com.oneteam.empsystem.repo.EmployeeRepoImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EditDepartmentServlet extends HttpServlet {

    private final DepartmentRepoImpl departmentRepo;
    private final EmployeeRepoImpl employeeRepo;

    public EditDepartmentServlet() {
        this.departmentRepo = new DepartmentRepoImpl();
        this.employeeRepo = new EmployeeRepoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));
        Department department = departmentRepo.findById(departmentId);
        request.setAttribute("department", department);
        request.setAttribute("allEmployees", employeeRepo.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/DepartmentsPages/editDepartment.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        /* // validation
        if(name == null || name.trim().isEmpty() || description == null || description.trim().isEmpty()){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/DepartmentsPages/editDepartment.jsp?error=department name is required");
            dispatcher.forward(request, response);
        }
        */

        Department department = departmentRepo.findById(departmentId);

        List<Employee> employees = new ArrayList<>();
        String[] employeeIds = request.getParameterValues("employeeIds");
        if (employeeIds != null) {
            for (String employeeId : employeeIds) {
                Employee employee = employeeRepo.findById(Long.parseLong(employeeId));
                employee.setDepartment(department);
                employeeRepo.update(employee);
                employees.add(employee);
            }
        }

        if (department != null) {
            department.setDepartmentId(departmentId);
            department.setName(name);
            department.setDescription(description);
            department.setEmployees(employees);
            departmentRepo.update(department);
        }

        response.sendRedirect(request.getContextPath() + "/departments"); // Redirect to employee list page
    }

}
