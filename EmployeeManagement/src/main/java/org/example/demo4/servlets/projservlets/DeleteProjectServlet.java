package org.example.demo4.servlets.projservlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.example.demo4.repo.DepartmentRepoImpl;
import org.example.demo4.repo.ProjectRepoImpl;

import java.io.IOException;

public class DeleteProjectServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Long projectId = Long.parseLong(request.getParameter("projectId"));
        ProjectRepoImpl projectRepo = new ProjectRepoImpl();
        projectRepo.remove(projectId);

        response.sendRedirect(request.getContextPath() + "/projects"); // Redirect to employees list page
    }

}
