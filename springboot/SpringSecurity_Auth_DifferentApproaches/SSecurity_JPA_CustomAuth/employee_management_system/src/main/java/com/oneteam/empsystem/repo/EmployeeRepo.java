package com.oneteam.empsystem.repo;

import com.oneteam.empsystem.entity.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
