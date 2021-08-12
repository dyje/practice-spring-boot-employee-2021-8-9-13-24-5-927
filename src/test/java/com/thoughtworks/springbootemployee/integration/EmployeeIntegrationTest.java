package com.thoughtworks.springbootemployee.integration;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;


    private List<Employee> mockEmployees;

    @BeforeEach
    public void data(){
        mockEmployees = Arrays.asList(
                (new Employee(162, "Jesse", 22,"male",500)),
                (new Employee(195, "Donna", 26,"female",500)),
                (new Employee(196, "Cathy", 23,"female",500)),
                (new Employee(197, "Jerry", 25,"male",500)),
                (new Employee(198, "Kael", 21,"male",500)),
                (new Employee(199, "Greg", 33,"male",500)),
                (new Employee(226, "Ramon", 21,"male",500))
        );
    }

    @Test
    void should_return_all_employees_when_getAllEmployees() throws Exception {
        //given
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Jesse"))
                .andExpect(jsonPath("$[0].age").value("22"))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value("500"));
    }

    @Test
    void should_return_employee_when_getEmployeeByID() throws Exception {
        //given
        int id = mockEmployees.get(0).getId();


        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jesse"))
                .andExpect(jsonPath("$.age").value(22))
                .andExpect(jsonPath("$.gender").value("male"))
                .andExpect(jsonPath("$.salary").value(500));
    }

    @Test
    void should_return_four_employees_when_getEmployeeByPage() throws Exception {
        //given
        int pageSize = 4;
        int page = 1;

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees")
                .param("page", String.valueOf(page)).param("pageSize", String.valueOf(pageSize))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(4)));

    }

    @Test
    void should_return_employees_when_getEmployeeByGender() throws Exception{
        //given
        String gender = "male";

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("gender", gender)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(4)));
    }
    






}
