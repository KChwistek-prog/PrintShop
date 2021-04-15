package com.printshopmanagement.application.controller;

import com.printshopmanagement.application.domain.Employee;
import com.printshopmanagement.application.domain.EmployeeDto;
import com.printshopmanagement.application.mapper.EmployeeMapper;
import com.printshopmanagement.application.repository.EmployeeDbService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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

    @Test
    public void testGetEmployee() throws Exception {
        Employee employee = new Employee(1L, 14324321L, "John", "Doe", "Elm Street", "Working", 2000L);
        EmployeeDto employeeDto = new EmployeeDto(1L, 14324321L, "John", "Doe", "Elm Street", "Working", 2000L);

        when(employeeDbService.getEmployee(any())).thenReturn(Optional.of(employee));
        when(employeeMapper.mapToEmployeeDto(employee)).thenReturn(employeeDto);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/getEmployee/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)));
    }
}
