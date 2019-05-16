package com.app.security.rest.daos;

import org.springframework.stereotype.Repository;

import com.app.security.rest.models.Employee;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDAO {

    private static List<Employee> list = new ArrayList<>();
    
    static {
        list.add(new Employee(1, "Tony", "Stark", "tony@gmail.com"));
        list.add(new Employee(2, "Bruice", "Banner", "abc@gmail.com"));
        list.add(new Employee(3, "David", "Kameron", "titanic@gmail.com"));
    }
    
    public List<Employee> getAllEmployees() {
        return list;
    }
    
    public void addEmployee(Employee employee) {
        list.add(employee);
    }
}
