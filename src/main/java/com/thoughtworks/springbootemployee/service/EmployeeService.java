package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public Employee getEmployeeByID(Integer employeeId) {
        return retiringEmployeeRepository.getAllEmployees()
                .stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .orElse(null);
    }

    public List<Employee> getEmployeeByPage(Integer page, Integer pageSize) {
        return retiringEmployeeRepository.getAllEmployees()
                .stream()
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return retiringEmployeeRepository.getAllEmployees()
                .stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public Employee addNewEmployee(Employee newEmployee) {
        Employee newEmployeeToBeAdded = new Employee(
                retiringEmployeeRepository.getAllEmployees().size() + 1, newEmployee.getName(), newEmployee.getAge(), newEmployee.getGender(), newEmployee.getSalary());
        retiringEmployeeRepository.getAllEmployees().add(newEmployeeToBeAdded);
        return newEmployeeToBeAdded;
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
