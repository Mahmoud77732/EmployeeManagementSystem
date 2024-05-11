package com.oneteam.empsystem.entity.mapper;


import com.oneteam.empsystem.entity.dto.ProjectDTO;
import com.oneteam.empsystem.entity.pojo.Project;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProjectMapper implements Function<Project, ProjectDTO> {

    @Override
    public ProjectDTO apply(Project project) {
        ProjectDTO projectDTO = new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription()
        );
        return projectDTO;
    }

}
