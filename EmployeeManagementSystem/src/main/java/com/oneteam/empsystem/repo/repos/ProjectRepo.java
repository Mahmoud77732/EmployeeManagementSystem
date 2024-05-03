package com.oneteam.empsystem.repo.repos;

import com.oneteam.empsystem.entity.Employee;
import com.oneteam.empsystem.entity.Project;

import java.util.Set;

public interface ProjectRepo extends GenericRepo<Project, Long> {

    Set<Employee> getEmployees(Long projectId);
}

