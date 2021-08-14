package com.thoughtworks.springbootemployee.integration;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    public void data() {
        employee = new Employee(1, "Jesse", 22, "male", 500);
        employeeService.addNewEmployee(employee);
        employee = new Employee(2, "Donna", 26, "female", 500);
        employeeService.addNewEmployee(employee);
        employee = new Employee(3, "Cathy", 23, "female", 500);
        employeeService.addNewEmployee(employee);
        employee = new Employee(4, "Jerry", 25, "male", 500);
        employeeService.addNewEmployee(employee);
        employee = new Employee(5, "Kael", 21, "male", 500);
        employeeService.addNewEmployee(employee);

    }

    @Test
    void should_return_all_employees_when_getAllEmployees() throws Exception {
        //given
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Jesse"))
                .andExpect(jsonPath("$[1].name").value("Donna"))
                .andExpect(jsonPath("$[2].name").value("Cathy"))
                .andExpect(jsonPath("$[3].name").value("Jerry"))
                .andExpect(jsonPath("$[4].name").value("Kael"));
    }

    @Test
    void should_return_employee_when_getEmployeeByID() throws Exception {
        //given
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", 1))
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
                .andExpect(jsonPath("$.*", hasSize(4)))
                .andExpect(jsonPath("$[0].name").value("Jesse"))
                .andExpect(jsonPath("$[1].name").value("Donna"))
                .andExpect(jsonPath("$[2].name").value("Cathy"))
                .andExpect(jsonPath("$[3].name").value("Jerry"));

    }

    @Test
    void should_return_employees_when_getEmployeeByGender() throws Exception {
        //given
        String gender = "male";

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees").param("gender", gender)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)))
                .andExpect(jsonPath("$[0].name").value("Jesse"))
                .andExpect(jsonPath("$[1].name").value("Jerry"))
                .andExpect(jsonPath("$[2].name").value("Kael"));
    }

    @Test
    void should_add_employee_when_addNewEmployee() throws Exception {
        //given
        String employee = "{\n" +
                "        \"id\": 227,\n" +
                "        \"name\": \"Rose\",\n" +
                "        \"age\": 21,\n" +
                "        \"gender\": \"female\",\n" +
                "        \"salary\": 1000\n" +
                "}";

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Rose"))
                .andExpect(jsonPath("$.age").value("21"))
                .andExpect(jsonPath("$.gender").value("female"))
                .andExpect(jsonPath("$.salary").value("1000"));
    }

    @Test
    void should_update_employee_info_when_updateEmployeeById() throws Exception {
        //given
        String newEmployeeInfo = "{\n" +
                "        \"name\": \"Updated Jesse\",\n" +
                "        \"salary\": 3000\n" +
                "}";
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{employeeID}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newEmployeeInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Jesse"))
                .andExpect(jsonPath("$.salary").value("3000"));
    }

    @Test
    void should_remove_employee_when_removeEmployee() throws Exception {
        //given
        final Employee employee = new Employee(228, "TestEmployee", 99, "male", 9999);
        final Employee addedEmployee = employeeService.addNewEmployee(employee);
        //when
        //then
        int employeeID = addedEmployee.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/{id}", employeeID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
