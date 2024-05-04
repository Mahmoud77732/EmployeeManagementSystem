package com.oneteam.empsystem.servlets.empservlets;

import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.repos.EmployeeRepo;
import com.oneteam.empsystem.repo.reposimpl.EmployeeRepoImpl;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;


public class EmployeeListServlet extends HttpServlet {

    private EmployeeRepo employeeRepo;

    public void init() {
        this.employeeRepo = new EmployeeRepoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            List<Employee> employees =  employeeRepo.findAll();
            HttpSession session = request.getSession(false);
            if(session.getAttribute("username") != null){
                String userRole = session.getAttribute("role").toString();
                request.setAttribute("userPageRole", userRole);
            }
            if(!employees.isEmpty()){
                request.setAttribute("employees", employees);
                request.getRequestDispatcher("/pages/EmployeePages/employee-list.jsp").forward(request, response);
            }
            else{
                request.setAttribute("employees", null);
                request.getRequestDispatcher("/pages/EmployeePages/employee-list.jsp").forward(request, response);
            }
        }
        catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }

}
