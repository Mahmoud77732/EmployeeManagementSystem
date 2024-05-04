package com.oneteam.empsystem.servlets.projservlets;

import com.oneteam.empsystem.repo.repos.ProjectRepo;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.entity.Project;
import com.oneteam.empsystem.repo.reposimpl.ProjectRepoImpl;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet("/addProject")
public class AddProjectServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Project newProject = new Project();
        newProject.setName(name);
        newProject.setDescription(description);
        Set<Employee> employees = new HashSet<>();
        newProject.setEmployees(employees);
        ProjectRepo projectRepo = new ProjectRepoImpl();
        projectRepo.save(newProject);

        response.sendRedirect(request.getContextPath() + "/projects"); // Redirect to employee list page
    }

}
