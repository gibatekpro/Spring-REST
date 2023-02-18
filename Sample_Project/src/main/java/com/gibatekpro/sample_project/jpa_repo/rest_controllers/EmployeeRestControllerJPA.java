package com.gibatekpro.sample_project.jpa_repo.rest_controllers;

import com.gibatekpro.sample_project.entity.Employee;
import com.gibatekpro.sample_project.jpa_repo.jpa_service.JPAEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jpa/api")
public class EmployeeRestControllerJPA {

    /** findAll()
     *  findById()
     *  save()
     *  deleteById()
     * */
    private JPAEmployeeService employeeService;

    @Autowired
    public EmployeeRestControllerJPA(JPAEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployeeList() {
        System.out.println("Inside here");
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeList(@PathVariable int employeeId) {

        return employeeService.findById(employeeId);
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployeeList(@RequestBody Employee employee, @PathVariable int employeeId) {

        employee.setId(employeeId);

        employeeService.save(employee);

        return employee;
    }

    @PostMapping("/employees")
    public Employee postEmployee(@RequestBody Employee employee) {

        employee.setId(0);

        employeeService.save(employee);

        return employee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String removeEmployee(@PathVariable int employeeId) {

        System.out.println("Inside Delete here");

        employeeService.deleteById(employeeId);

        return "Complete";
    }

}
