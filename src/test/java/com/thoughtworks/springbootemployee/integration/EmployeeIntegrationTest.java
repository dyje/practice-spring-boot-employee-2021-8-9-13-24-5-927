package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;


    @Test
    void should_return_all_employees_when_getAllEmployees() throws Exception {
        //given
        final Employee employee = new Employee(1, "Ramon", 21, "Male", 9000);
        employeeRepository.save(employee);

        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("Ramon"))
                .andExpect(jsonPath("$[0].age").value("21"))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value("9000"));
    }

    @Test
    void should_add_employee_when_addNewEmployee() throws Exception {
        //given
        String employee = "{\n" +
                "        \"id\": 70\n" +
                "        \"name\": \"Jisoo\",\n" +
                "        \"age\": 28,\n" +
                "        \"gender\": \"female\",\n" +
                "        \"salary\": 100\n" +
                "}";

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jisoo"))
                .andExpect(jsonPath("$.age").value("28"))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value("100"));
    }


}
