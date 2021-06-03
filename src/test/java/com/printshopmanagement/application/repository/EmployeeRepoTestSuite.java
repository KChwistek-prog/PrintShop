package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Employee;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@SpringBootTest
@ExtendWith(SpringExtension.class)
class EmployeeRepoTestSuite {

    @Autowired
    private EmployeeDbService employeeDbService;

    private Employee employee;

    @BeforeEach
    public void init(){
        employee = new Employee(1L, 231541521L, "John", "Doe", "Elm Str", "Working", 2500L);
    }

//    @Test
//    void shouldAddEmployee(){
//
//        employeeDbService.saveEmployee(employee);
//        //Then
//        Assertions.assertTrue(employeeDbService.getEmployee(1L).isPresent());
//
//        employeeDbService.deleteEmployee(1L);
//    }

    @Test
    public void shouldDeleteEmployee(){

        employeeDbService.saveEmployee(employee);
        //When
        employeeDbService.deleteEmployee(1L);
        //Then
        Assertions.assertFalse(employeeDbService.getEmployee(1L).isPresent());
    }
}
