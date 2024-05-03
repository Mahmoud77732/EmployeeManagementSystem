package com.oneteam.empsystem.servlets.projservlets;

import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.entity.Project;
import com.oneteam.empsystem.repo.reposimpl.EmployeeRepoImpl;
import com.oneteam.empsystem.repo.repos.GenericRepo;
import com.oneteam.empsystem.repo.reposimpl.GenericRepoImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Set;

public class DeleteProjectServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Long projectId = Long.parseLong(request.getParameter("projectId"));
        GenericRepo<Project, Long> projectGenericRepo = new GenericRepoImpl<>(Project.class);
        EmployeeRepoImpl employeeRepo = new EmployeeRepoImpl();
        Project project = projectGenericRepo.findById(projectId);

        Set<Employee> employees = project.getEmployees();
        for(Employee emp : employees){
            emp.getProjects().remove(project);
            employeeRepo.update(emp);
        }
        project.setEmployees(null);
        projectGenericRepo.update(project);

        projectGenericRepo.remove(projectGenericRepo.findById(projectId));

        response.sendRedirect(request.getContextPath() + "/projects"); // Redirect to employees list page
    }

}
