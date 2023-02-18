package com.gibatekpro.sample_project.service;

import com.gibatekpro.sample_project.dao.EmployeeDAO;
import com.gibatekpro.sample_project.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Transactional
    @Override
    public List<Employee> getEmployees() {
        return employeeDAO.getEmployees();
    }

    @Transactional
    @Override
    public Employee findEmployee(int id) {
        return employeeDAO.findEmployee(id);
    }

    @Transactional
    @Override
    public void addEmployee(Employee employee) {

        employeeDAO.addEmployee(employee);

    }

    @Transactional
    @Override
    public void updateEmployee(Employee employee) {

        employeeDAO.updateEmployee(employee);

    }

    @Transactional
    @Override
    public void deleteEmployee(int id) {

        employeeDAO.deleteEmployee(id);

    }
}
