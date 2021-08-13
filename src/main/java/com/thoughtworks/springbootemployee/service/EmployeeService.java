package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private RetiringEmployeeRepository retiringEmployeeRepository;
    private Integer employeeId;
    @Autowired
    private EmployeeRepository employeeRepository;
    private EmployeeNotFoundException employeeNotFoundException;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeByID(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(()->new EmployeeNotFoundException("Employee ID not found."));
    }

    public List<Employee> getEmployeeByPage(Integer page, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(page - 1, pageSize)).getContent();
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public Employee addNewEmployee(Employee newEmployee) {
        return employeeRepository.save(newEmployee);
    }

    public Employee updateEmployeeById(Integer employeeId, Employee employeeUpdate) {
        Employee updatedEmployee = employeeRepository.findById(employeeId)
                .map(employee -> updateEmployeeInfo(employee, employeeUpdate))
                .get();
        return employeeRepository.save(updatedEmployee);

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
        if (employeeUpdate.getCompanyId() != null) {
            employee.setCompanyId(employeeUpdate.getCompanyId());
        }
        return employee;
    }

    public Employee removeEmployee(Integer employeeId) {
        Employee employeeToBeRemoved = getAllEmployees()
                .stream()
                .filter(employee -> employee.getId()
                        .equals(employeeId))
                .findFirst()
                .orElse(null);
        if (employeeToBeRemoved != null)
            employeeRepository.delete(employeeToBeRemoved);
        return employeeToBeRemoved;
    }
}
