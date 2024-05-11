package com.oneteam.empsystem.repo.repo_interface;

import com.oneteam.empsystem.entity.pojo.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Long> {
}
