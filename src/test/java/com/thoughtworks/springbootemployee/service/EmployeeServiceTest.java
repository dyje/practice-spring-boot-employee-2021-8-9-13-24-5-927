package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Test
    public void should_return_all_employees_when_getAllEmployees_given_all_employees() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", 25, "Female", 10000));
        employees.add(new Employee(2, "Bob", 25, "Male", 10000));
        employees.add(new Employee(3, "Catnice", 25, "Female", 10000));
        given(employeeRepository.getAllEmployees()).willReturn(employees);
        //when
        List<Employee> actualEmployees = employeeService.getAllEmployees();
        //then
        assertEquals(employees, actualEmployees);
    }

    @Test
    public void should_return_an_employees_when_getEmployeeByID_given_an_employee_id() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", 25, "Female", 10000));
        employees.add(new Employee(2, "Bob", 25, "Male", 10000));
        employees.add(new Employee(3, "Catnice", 25, "Female", 10000));
        given(employeeRepository.getAllEmployees()).willReturn(employees);

        //when
        Employee filteredEmployee = employeeService.getEmployeeByID(1);

        //then
        assertEquals(employees.get(0), filteredEmployee);

    }
    
    @Test
    void should_return_expected_number_only_when_getEmployeeByPage_given_page_size_three_and_page_index_one() {
        //given
        List<Employee> employees = new ArrayList<>();
        given(employeeRepository.getAllEmployees()).willReturn(Arrays.asList(new Employee(),new Employee(),new Employee(),new Employee(), new Employee(), new Employee()));
        int expectedCountOfEmployees = 3;

        //when
        int expectedEmployees = employeeService.getEmployeeByPage(1,3).size();
        
        //then
        assertEquals(expectedEmployees, expectedCountOfEmployees);
        
    }
    @Test
    void should_return_male_employees_when_getEmployeeByGender_given_mixed_of_male_and_female_employees() {
        //given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Alice", 25, "Female", 10000));
        employees.add(new Employee(2, "Bob", 25, "Male", 10000));
        employees.add(new Employee(3, "Catnice", 25, "Female", 10000));
        employees.add(new Employee(4, "Donnie", 30, "Male", 20000));
        given(employeeRepository.getAllEmployees()).willReturn(employees);
        String gender = "Male";

        //when
        List<Employee> filteredMaleEmployees = employeeService.getEmployeeByGender(gender);

        //then
        assertEquals(2, filteredMaleEmployees
                                .stream()
                                .map(Employee::getGender)
                                .filter(employeeGender -> employeeGender.equals("Male")).count());
        assertEquals(employees.get(0).getGender(), filteredMaleEmployees.get(0).getGender());
        assertEquals(employees.get(1).getGender(), filteredMaleEmployees.get(1).getGender());

    }
    
    


    }
