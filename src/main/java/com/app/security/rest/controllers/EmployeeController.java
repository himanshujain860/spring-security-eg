package com.app.security.rest.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.security.rest.daos.EmployeeDAO;
import com.app.security.rest.models.Employee;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    @Autowired
    private EmployeeDAO employeeDao;
    
    @GetMapping(path="/", produces = "application/json")
    public List<Employee> getEmployees() {
        return employeeDao.getAllEmployees();
    }
    
    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public Employee addEmployee(@RequestBody Employee employee) throws Exception {
        Integer id = employeeDao.getAllEmployees().size() + 1;
        employee.setId(id);
        employeeDao.addEmployee(employee);
        return employee;
    }
}
