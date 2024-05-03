package com.oneteam.empsystem.servlets.depservlets;

import com.oneteam.empsystem.repo.repos.DepartmentRepo;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.oneteam.empsystem.entity.Department;
import com.oneteam.empsystem.repo.reposimpl.DepartmentRepoImpl;

import java.io.IOException;
import java.util.List;

public class DepartmentListServlet extends HttpServlet {

    private final DepartmentRepo departmentRepo;

    public DepartmentListServlet() {
        this.departmentRepo = new DepartmentRepoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> departments =  departmentRepo.findAll();
        if(!departments.isEmpty()){
            request.setAttribute("departments", departments);
            request.getRequestDispatcher("/pages/DepartmentsPages/departments-list.jsp").forward(request, response);
        }
        else{
            request.setAttribute("departments", null);
            request.getRequestDispatcher("/pages/DepartmentsPages/departments-list.jsp").forward(request, response);
        }
    }
}
