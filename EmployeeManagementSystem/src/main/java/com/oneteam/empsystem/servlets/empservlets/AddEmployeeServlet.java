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
    private final UserRepo userRepo = new UserRepoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         response.sendError(405);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String salaryStr = request.getParameter("salary");
        String departmentName = request.getParameter("departmentName");

        HttpSession session = request.getSession();
        request.setAttribute("error", "");

        if(name == null || email == null || password == null || role == null || salaryStr == null
            || name.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty() || role.trim().isEmpty()
            || departmentName.trim().isEmpty())
        {
            request.setAttribute("error", "All fields are required");
            request.getRequestDispatcher("/pages/EmployeePages/addEmployee.jsp").forward(request, response);
        }
        else{
            try{
                BigDecimal salary = new BigDecimal(salaryStr);
                Employee newEmployee = new Employee(name, email, salary);
                // add this employee to it's department
                Department department = departmentRepo.findDepartmentByName(departmentName);
                if(department != null){ // department found
                    department.setName(departmentName);
                    newEmployee.setDepartment(department);
                    department.getEmployees().add(newEmployee);
                    departmentRepo.update(department);
                }
                else{ // department not found
                    session.setAttribute("error", "department not found");
                    request.getRequestDispatcher("/pages/EmployeePages/addEmployee.jsp").forward(request, response);
                }
                saveUser(newEmployee.getName(), password, role); // save user info
                employeeRepo.save(newEmployee);
                response.sendRedirect(request.getContextPath() + "/employees"); // Redirect to employee list page
            }
            catch(Exception ex){
                System.err.println(ex.getMessage());
                request.setAttribute("error", ex.getMessage());
                request.getRequestDispatcher("/pages/EmployeePages/addEmployee.jsp").forward(request, response);
            }
        }
    }


    protected void saveUser(String username, String password, String role){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // by default
        user.setRole(role);
        userRepo.save(user);
    }
}
