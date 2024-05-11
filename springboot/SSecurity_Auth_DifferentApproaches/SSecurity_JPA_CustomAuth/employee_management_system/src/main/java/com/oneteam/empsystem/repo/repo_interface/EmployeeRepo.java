/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oneteam.empsystem.repo.repo_interface;

import com.oneteam.empsystem.entity.pojo.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mahmoud
 */
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}
