package com.oneteam.empsystem.repo;

import com.oneteam.empsystem.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project,Long> {
}
