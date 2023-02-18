package com.gibatekpro.sample_project.rest_controller;

import com.gibatekpro.sample_project.dao.EmployeeDAO;
import com.gibatekpro.sample_project.entity.Employee;
import com.gibatekpro.sample_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> getEmployeeList() {
        System.out.println("Inside here");
        return employeeService.getEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeList(@PathVariable int employeeId) {

        return employeeService.findEmployee(employeeId);
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployeeList(@RequestBody Employee employee, @PathVariable int employeeId) {

        employee.setId(employeeId);

        employeeService.updateEmployee(employee);

        return employee;
    }

    @PostMapping("/employees")
    public Employee postEmployee(@RequestBody Employee employee) {

        employee.setId(0);

        employeeService.addEmployee(employee);

        return employee;
    }

    @DeleteMapping("/employees/{employeeId}")
    public String removeEmployee(@PathVariable int employeeId) {

        System.out.println("Inside Delete here");

        employeeService.deleteEmployee(employeeId);

        return "Complete";
    }

}
