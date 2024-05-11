package com.oneteam.empsystem.repo.repo_interface;

import com.oneteam.empsystem.entity.pojo.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project,Long> {
}
