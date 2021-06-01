package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class EmployeeRepoTest {

    @Autowired
    private EmployeeDbService employeeDbService;

    public Employee employee;

    @BeforeEach
    public void setUp(){
        employee = new Employee(1L, 231541521L, "John", "Doe", "Elm Str", "Working", 2500L);
    }

    @Test
    public void shouldAddEmployee(){
        //When
        employeeDbService.saveEmployee(employee);
        //Then
        Assertions.assertEquals(employeeDbService.getEmployee(1L).get().getEmployeeFirstName(), employee.getEmployeeFirstName());
        //CleanUp
        employeeDbService.deleteEmployee(1L);
    }

    @Test
    public void shouldDeleteEmployee(){
        //Given
        employeeDbService.saveEmployee(employee);
        //When
        employeeDbService.deleteEmployee(1L);
        //Then
        Assertions.assertEquals(employeeDbService.getEmployee(1l), Optional.empty());
    }

}
