package org.example.demo4.servlets.projservlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.example.demo4.entity.Project;
import org.example.demo4.entity.Employee;
import org.example.demo4.repo.EmployeeRepoImpl;
import org.example.demo4.repo.ProjectRepoImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EditProjectServlet extends HttpServlet {

    private final ProjectRepoImpl projectRepo;
    private final EmployeeRepoImpl employeeRepo;

    public EditProjectServlet() {
        this.projectRepo = new ProjectRepoImpl();
        this.employeeRepo = new EmployeeRepoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long projectId = Long.parseLong(request.getParameter("projectId"));
        Project project = projectRepo.findById(projectId);
        request.setAttribute("project", project);
        request.setAttribute("allEmployees", employeeRepo.findAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/ProjectsPages/editProject.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long projectId = Long.parseLong(request.getParameter("projectId"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");


        Project project = projectRepo.findById(projectId);

        Set<Employee> employees = new HashSet<>();
        String[] employeeIds = request.getParameterValues("employeeIds");
        if (employeeIds != null) {
            for (String employeeId : employeeIds) {
                Employee employee = employeeRepo.findById(Long.parseLong(employeeId));
                // employee.setProjects();
                // employeeRepo.update(employee);
                employees.add(employee);
            }
        }

        if (project != null) {
            project.setProjectId(projectId);
            project.setName(name);
            project.setDescription(description);
            // project.setEmployees(employees);
            projectRepo.update(project);
        }

        response.sendRedirect(request.getContextPath() + "/projects"); // Redirect to employee list page
    }

}
