package org.example.demo4.servlets.projservlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.example.demo4.entity.Department;
import org.example.demo4.entity.Employee;
import org.example.demo4.entity.Project;
import org.example.demo4.repo.DepartmentRepoImpl;
import org.example.demo4.repo.ProjectRepoImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        // System.out.println("======> Employees:" + newDepartment.toString());
        ProjectRepoImpl projectRepo = new ProjectRepoImpl();
        projectRepo.save(newProject);

        response.sendRedirect(request.getContextPath() + "/projects"); // Redirect to employee list page
    }

}
