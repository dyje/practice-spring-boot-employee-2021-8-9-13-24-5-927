package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final List<Employee> employees = new ArrayList<>();
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{employeeID}")
    public Employee getEmployeeByID (@PathVariable Integer employeeID){
        return employeeService.getEmployeeByID(employeeID);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getEmployeeByPage (@RequestParam Integer page, @RequestParam Integer pageSize){
        return employeeService.getEmployeeByPage(page,pageSize);
    }

    @GetMapping(params = {"gender"} )
    public List<Employee> getEmployeeByGender (@RequestParam String gender){
        return employees.stream()
                .filter(employee -> employee.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }

    @PostMapping
    public Employee addNewEmployee (@RequestBody Employee employee){
        employees.add(employee);

        return employee;
    }

}
