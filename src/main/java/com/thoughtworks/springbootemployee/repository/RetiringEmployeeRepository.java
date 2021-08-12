package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
@Repository
public class RetiringEmployeeRepository {
    private List<Employee> employees = new ArrayList<>();
    public RetiringEmployeeRepository(){
        employees.add(new Employee(1, "Alice", 25, "Female", 10000));
        employees.add(new Employee(2, "Bob", 25, "Male", 10000));
        employees.add(new Employee(3, "Catnice", 25, "Female", 10000));
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }

}
