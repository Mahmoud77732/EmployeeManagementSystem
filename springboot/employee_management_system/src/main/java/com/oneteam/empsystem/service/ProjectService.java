package com.oneteam.empsystem.service;

import com.oneteam.empsystem.entity.pojo.Project;
import com.oneteam.empsystem.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepo.findById(id);
    }

    public Project saveProject(Project project) {
        return projectRepo.save(project);
    }

    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }
}