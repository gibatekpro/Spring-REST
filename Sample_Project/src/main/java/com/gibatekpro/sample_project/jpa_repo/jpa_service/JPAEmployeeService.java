package com.gibatekpro.sample_project.jpa_repo.jpa_service;

import com.gibatekpro.sample_project.entity.Employee;

import java.util.List;

public interface JPAEmployeeService {

    /** findAll()
     *  findById()
     *  save()
     *  deleteById()
     * */

    public List<Employee> findAll();

    public Employee findById(int id);

    public void save(Employee employee);

    public void deleteById(int id);


}
