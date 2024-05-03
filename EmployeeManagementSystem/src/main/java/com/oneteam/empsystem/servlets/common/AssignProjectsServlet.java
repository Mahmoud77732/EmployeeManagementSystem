package com.oneteam.empsystem.servlets.common;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.entity.Project;
import com.oneteam.empsystem.repo.EmployeeRepoImpl;
import com.oneteam.empsystem.repo.ProjectRepoImpl;

import java.io.IOException;
import java.util.List;

public class AssignProjectsServlet extends HttpServlet {

    private final EmployeeRepoImpl employeeRepo;
    private final ProjectRepoImpl projectRepo;

    public AssignProjectsServlet() {
        employeeRepo = new EmployeeRepoImpl();
        projectRepo = new ProjectRepoImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = employeeRepo.findAll();
        List<Project> projects = projectRepo.findAll();
        System.out.println("====> employees: " + employees.size());
        System.out.println("====> projects: " + projects.size());
        request.setAttribute("employees", employees);
        request.setAttribute("projects", projects);
        request.getRequestDispatcher("/pages/common/assignProjects.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=======> " + request.getParameter("id"));
        System.out.println("=======> " + request.getParameter("projectId"));
        Long employeeId = Long.parseLong(request.getParameter("id"));
        Long projectId = Long.parseLong(request.getParameter("projectId"));

        System.out.println("====> emp_id = " + employeeId);
        System.out.println("====> pro_id = " + projectId);

        Employee employee = employeeRepo.findById(employeeId);
        Project project = projectRepo.findById(projectId);

        System.out.println("====> emp_name = " + employee.getName());
        System.out.println("====> pro_name = " + project.getName());

        // Assign project to employee
        employee.getProjects().add(project);
        employeeRepo.update(employee);
        project.getEmployees().add(employee);
        projectRepo.update(project);

        response.sendRedirect("assignProjects"); // Redirect to the assignment page
    }


}
