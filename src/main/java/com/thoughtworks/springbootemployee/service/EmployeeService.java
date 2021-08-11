package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private Integer employeeId;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public Employee getEmployeeByID(Integer employeeId){
        return employeeRepository.getAllEmployees()
                .stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }
}
