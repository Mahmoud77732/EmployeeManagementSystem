package com.oneteam.empsystem.servlets.projservlets;

import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.entity.Project;
import com.oneteam.empsystem.repo.EmployeeRepoImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.oneteam.empsystem.repo.ProjectRepoImpl;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class DeleteProjectServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Long projectId = Long.parseLong(request.getParameter("projectId"));
        ProjectRepoImpl projectRepo = new ProjectRepoImpl();
        EmployeeRepoImpl employeeRepo = new EmployeeRepoImpl();
        Project project = projectRepo.findById(projectId);

        Set<Employee> employees = project.getEmployees();
        for(Employee emp : employees){
            emp.getProjects().remove(project);
            employeeRepo.update(emp);
        }
        project.setEmployees(null);
        projectRepo.update(project);

        projectRepo.remove(projectId);

        response.sendRedirect(request.getContextPath() + "/projects"); // Redirect to employees list page
    }

}
