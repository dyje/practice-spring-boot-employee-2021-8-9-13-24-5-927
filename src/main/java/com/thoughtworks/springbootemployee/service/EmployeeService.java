package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Employee> getEmployeeByPage(Integer page, Integer pageSize) {
        return employeeRepository.getAllEmployees()
                .stream()
                .skip((page -1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeeByGender(String gender) {
        return employeeRepository.getAllEmployees()
                .stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public Employee addNewEmployee(Employee newEmployee) {
        Employee newEmployeeToBeAdded = new Employee(
                employeeRepository.getAllEmployees().size()+1, newEmployee.getName(), newEmployee.getAge(), newEmployee.getGender(), newEmployee.getSalary());
        employeeRepository.getAllEmployees().add(newEmployeeToBeAdded);
        return newEmployeeToBeAdded;
    }

    public Employee findById (Integer employeeId, Employee employeeUpdate){
        return employeeRepository.getAllEmployees()
                .stream()
                .filter(employee -> employee.getId().equals(employeeId))
                .findFirst()
                .map(employee -> updateEmployeeInfo(employee, employeeUpdate))
                .orElse(null);

    }

    public Employee updateEmployeeInfo (Employee employee, Employee employeeUpdate){
        if (employeeUpdate.getName() != null){
            employee.setName(employeeUpdate.getName());
        }
        if (employeeUpdate.getAge() != null){
            employee.setAge(employeeUpdate.getAge());
        }
        if (employeeUpdate.getGender() != null){
            employee.setGender(employeeUpdate.getGender());
        }
        if (employeeUpdate.getSalary() != null){
            employee.setSalary(employeeUpdate.getSalary());
        }
        return employee;
    }

    public Employee remvoveEmployee(Integer employeeId) {
        return null;
    }
}
