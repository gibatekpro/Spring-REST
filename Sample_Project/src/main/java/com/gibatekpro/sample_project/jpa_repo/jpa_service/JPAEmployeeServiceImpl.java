package com.gibatekpro.sample_project.jpa_repo.jpa_service;

import com.gibatekpro.sample_project.dao.EmployeeDAO;
import com.gibatekpro.sample_project.entity.Employee;
import com.gibatekpro.sample_project.jpa_repo.spring_dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class JPAEmployeeServiceImpl implements JPAEmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public JPAEmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //No need for @Transactional because Jpa Repo handles that
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    //No need for @Transactional because Jpa Repo handles that
    @Override
    public Employee findById(int id) {

        Optional<Employee> result = employeeRepository.findById(id);

        return result.orElse(null);
    }

    //No need for @Transactional because Jpa Repo handles that
    @Override
    public void save(Employee employee) {

        employeeRepository.save(employee);

    }

    //No need for @Transactional because Jpa Repo handles that
    @Override
    public void deleteById(int id) {

        employeeRepository.deleteById(id);

    }
}
