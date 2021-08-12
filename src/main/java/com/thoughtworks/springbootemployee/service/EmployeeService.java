package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private RetiringEmployeeRepository retiringEmployeeRepository;
    private Integer employeeId;
    @Autowired
    private EmployeeRepository employeeRepository;
    public EmployeeService(RetiringEmployeeRepository retiringEmployeeRepository) {
        this.retiringEmployeeRepository = retiringEmployeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeByID(Integer employeeId) {
        return employeeRepository.findById(employeeId);
    }

//    public Page<Employee> getEmployeeByPage(Integer page, Integer pageSize) {
//        Pageable pageable = (Pageable) PageRequest.of(page, pageSize);
//        return employeeRepository.findAll((org.springframework.data.domain.Pageable) pageable);
//    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public Employee addNewEmployee(Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    public Employee findById(Integer employeeId, Employee employeeUpdate) {
        return retiringEmployeeRepository.getAllEmployees()
                .stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .map(employee -> updateEmployeeInfo(employee, employeeUpdate))
                .orElse(null);

    }
    
    public Employee updateEmployeeInfo(Employee employee, Employee employeeUpdate) {
        if (employeeUpdate.getName() != null) {
            employee.setName(employeeUpdate.getName());
        }
        if (employeeUpdate.getAge() != null) {
            employee.setAge(employeeUpdate.getAge());
        }
        if (employeeUpdate.getGender() != null) {
            employee.setGender(employeeUpdate.getGender());
        }
        if (employeeUpdate.getSalary() != null) {
            employee.setSalary(employeeUpdate.getSalary());
        }
        return employee;
    }

    public Employee removeEmployee(Integer employeeId) {
        Employee employeeToBeRemoved = retiringEmployeeRepository.getAllEmployees()
                .stream()
                .filter(employee -> employee.getId()
                        .equals(employeeId))
                .findFirst()
                .orElse(null);
        if (employeeToBeRemoved != null)
            retiringEmployeeRepository.getAllEmployees().remove(employeeToBeRemoved);
        return employeeToBeRemoved;
    }
}
