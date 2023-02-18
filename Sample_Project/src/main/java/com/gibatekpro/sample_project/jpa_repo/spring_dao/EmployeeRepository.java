package com.gibatekpro.sample_project.jpa_repo.spring_dao;

import com.gibatekpro.sample_project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {



}
