package com.oneteam.empsystem.servlets.depservlets;

import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.entity.Employee;

import com.oneteam.empsystem.repo.repos.DepartmentRepo;
import com.oneteam.empsystem.repo.repos.EmployeeRepo;
import com.oneteam.empsystem.repo.reposimpl.DepartmentRepoImpl;
import com.oneteam.empsystem.repo.reposimpl.EmployeeRepoImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/updateDepartment")
public class EditDepartmentServlet extends HttpServlet {

    private final DepartmentRepo departmentRepo;
    private final EmployeeRepo employeeRepo;

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
        Department department = departmentRepo.findById(departmentId);

        List<Employee> employees = new ArrayList<>();
        if(!department.getEmployees().isEmpty()){
            employees.addAll(department.getEmployees());
        }
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
