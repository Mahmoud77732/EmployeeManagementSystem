package com.oneteam.empsystem.servlets.empservlets;

import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.repo.repos.EmployeeRepo;
import com.oneteam.empsystem.repo.reposimpl.EmployeeRepoImpl;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
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
                /*
                when you use '/' before the _path_ then you want to go to another context
                if you didn't use it then you tell it that there is an endpoint on this context
                */
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
