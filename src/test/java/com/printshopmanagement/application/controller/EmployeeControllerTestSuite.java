package com.printshopmanagement.application.controller;

import com.google.gson.Gson;
import com.printshopmanagement.application.domain.Employee;
import com.printshopmanagement.application.domain.EmployeeDto;
import com.printshopmanagement.application.mapper.EmployeeMapper;
import com.printshopmanagement.application.repository.EmployeeDbService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(EmployeeController.class)
@RunWith(SpringRunner.class)
public class EmployeeControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeDbService employeeDbService;

    @MockBean
    private EmployeeMapper employeeMapper;

    private Employee employee;
    private EmployeeDto employeeDto;
    private Gson gson;

    @Before
    public void setUp() {
        gson = new Gson();
        employee = new Employee(1L, 14324321L, "John", "Doe", "Elm Street", "Working", 2000L);
        employeeDto = new EmployeeDto(1L, 14324321L, "John", "Doe", "Elm Street", "Working", 2000L);
        when(employeeMapper.mapToEmployee(any())).thenReturn(employee);
        when(employeeMapper.mapToEmployeeDto(any())).thenReturn(employeeDto);
    }

    @Test
    public void testGetEmployee() throws Exception {
        //Then
        when(employeeDbService.getEmployee(any())).thenReturn(Optional.of(employee));
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/getEmployee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeFirstName", Matchers.is("John")));
    }

    @Test
    public void testGetEmployees() throws Exception {
        //Given
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employeeDtoList.add(employeeDto);
        employeeDtoList.add(employeeDto);
        //When
        when(employeeMapper.mapToEmployeeListDto(any())).thenReturn(employeeDtoList);
        //Then
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/getEmployees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void testAddEmployee() throws Exception {
        //Given
        String employeeJson = gson.toJson(employee);
        when(employeeDbService.saveEmployee(any())).thenReturn(employee);
        //Then
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(employeeJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        //GivenWhenThen
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/deleteEmployee")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        //given
        Employee updatedEmployee = new Employee(1L, 14324321L, "UpdatedJohn", "Doe", "Elm Street", "Working", 2000L);
        EmployeeDto updatedEmployeeDto = new EmployeeDto(1L, 14324321L, "UpdatedJohn", "Doe", "Elm Street", "Working", 2000L);
        String updatedEmployeeJson = gson.toJson(updatedEmployee);
        //When
        when(employeeMapper.mapToEmployee(any())).thenReturn(updatedEmployee);
        when(employeeMapper.mapToEmployeeDto(any())).thenReturn(updatedEmployeeDto);
        when(employeeDbService.saveEmployee(updatedEmployee)).thenReturn(updatedEmployee);
        //Then
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/updateEmployee")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedEmployeeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeFirstName", Matchers.is("UpdatedJohn")));
    }
}
