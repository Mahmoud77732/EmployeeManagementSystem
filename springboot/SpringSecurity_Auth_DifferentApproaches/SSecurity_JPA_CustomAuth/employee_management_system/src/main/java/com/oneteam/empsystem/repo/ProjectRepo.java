package com.oneteam.empsystem.repo;

import com.oneteam.empsystem.entity.pojo.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project,Long> {
}
