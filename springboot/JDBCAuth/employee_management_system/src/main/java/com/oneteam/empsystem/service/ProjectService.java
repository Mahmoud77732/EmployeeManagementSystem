package com.oneteam.empsystem.service;

import com.oneteam.empsystem.entity.dto.ProjectDTO;
import com.oneteam.empsystem.entity.mapper.ProjectMapper;
import com.oneteam.empsystem.entity.pojo.Project;
import com.oneteam.empsystem.repo.repo_interface.ProjectRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private ProjectMapper projectMapper;


    //TODO using DTO
    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepo.findAll();
        return projects.stream()
                .map(projectMapper)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));
        return projectMapper.apply(project);
    }

    public ProjectDTO saveProject(Project project) {
        Project savedProject = projectRepo.save(project);
        return projectMapper.apply(savedProject);
    }


    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }
}