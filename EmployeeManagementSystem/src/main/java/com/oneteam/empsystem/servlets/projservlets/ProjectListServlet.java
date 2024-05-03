package com.oneteam.empsystem.servlets.projservlets;

import com.oneteam.empsystem.repo.repos.ProjectRepo;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.oneteam.empsystem.entity.Project;
import com.oneteam.empsystem.repo.reposimpl.ProjectRepoImpl;

import java.io.IOException;
import java.util.List;

public class ProjectListServlet extends HttpServlet {

    private final ProjectRepo projectRepo;

    public ProjectListServlet() {
        this.projectRepo = new ProjectRepoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Project> projects =  projectRepo.findAll();
        if(!projects.isEmpty()){
            request.setAttribute("projects", projects);
            request.getRequestDispatcher("/pages/ProjectsPages/projects-list.jsp").forward(request, response);
        }
        else{
            request.setAttribute("projects", null);
            request.getRequestDispatcher("/pages/ProjectsPages/projects-list.jsp").forward(request, response);
        }
    }
}
