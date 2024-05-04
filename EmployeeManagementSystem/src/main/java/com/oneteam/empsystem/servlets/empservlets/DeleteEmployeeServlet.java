package com.oneteam.empsystem.servlets.empservlets;

import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.entity.User;
import com.oneteam.empsystem.repo.repos.DepartmentRepo;
import com.oneteam.empsystem.repo.repos.EmployeeRepo;
import com.oneteam.empsystem.repo.repos.UserRepo;
import com.oneteam.empsystem.repo.reposimpl.DepartmentRepoImpl;
import com.oneteam.empsystem.repo.reposimpl.EmployeeRepoImpl;

import com.oneteam.empsystem.repo.reposimpl.UserRepoImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long employeeId = Long.parseLong(request.getParameter("id"));
        EmployeeRepo employeeRepo = new EmployeeRepoImpl();
        DepartmentRepo departmentRepo = new DepartmentRepoImpl();
        Employee employee = employeeRepo.findById(employeeId);
        // delete this employee from it's department also
        Department department = (employee.getDepartment() == null) ? null : employee.getDepartment();
        if(department != null){
            department.getEmployees().remove(employee);
            departmentRepo.update(department);
        }
        // delete user also
        UserRepo userRepo = new UserRepoImpl();
        User user = userRepo.findByUsername(employee.getName());
        userRepo.remove(user);
        employeeRepo.remove(employee);

        response.sendRedirect(request.getContextPath() + "/employees"); // Redirect to employees list page
    }
}
