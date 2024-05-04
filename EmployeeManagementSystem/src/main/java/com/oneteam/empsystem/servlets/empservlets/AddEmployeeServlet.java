package com.oneteam.empsystem.servlets.empservlets;

import com.oneteam.empsystem.entity.User;
import com.oneteam.empsystem.repo.repos.DepartmentRepo;
import com.oneteam.empsystem.repo.repos.EmployeeRepo;
import com.oneteam.empsystem.repo.repos.UserRepo;
import com.oneteam.empsystem.repo.reposimpl.DepartmentRepoImpl;
import com.oneteam.empsystem.repo.reposimpl.UserRepoImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.reposimpl.EmployeeRepoImpl;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/addEmployee")
public class AddEmployeeServlet extends HttpServlet {

    private final EmployeeRepo employeeRepo = new EmployeeRepoImpl();
    private final DepartmentRepo departmentRepo = new DepartmentRepoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(405);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        BigDecimal salary = new BigDecimal(request.getParameter("salary"));

        Employee newEmployee = new Employee();
        newEmployee.setName(name);
        newEmployee.setEmail(email);
        newEmployee.setSalary(salary);
        // add this employee to it's department
        if(!request.getParameter("departmentName").trim().isEmpty()){
            String departmentName = (request.getParameter("departmentName"));
            Department department = departmentRepo.findDepartmentByName(departmentName);
            department.setName(departmentName);
            newEmployee.setDepartment(department);
            department.getEmployees().add(newEmployee);
            departmentRepo.update(department);
        }
        else{
            newEmployee.setDepartment(null);
        }

        UserRepo userRepo = new UserRepoImpl();
        User user = new User();
        user.setUsername(newEmployee.getName());
        user.setPassword(password); // by default
        user.setRole(role);
        userRepo.save(user);

        employeeRepo.save(newEmployee);
        response.sendRedirect(request.getContextPath() + "/employees"); // Redirect to employee list page
    }
}
