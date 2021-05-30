package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeRepoTest {

    private final EmployeeDbService employeeDbService;

    @Autowired
    public EmployeeRepoTest(EmployeeDbService employeeDbService) {
        this.employeeDbService = employeeDbService;
    }

    @Test
    public void shouldAddEmployee(){
        //Given
        Employee employee = new Employee(1L, 231541521L, "John", "Doe", "Elm Str", "Working", 2500L);
        //When
        employeeDbService.saveEmployee(employee);
        //Then
        Assertions.assertEquals(employeeDbService.getEmployee(1L).get().getEmployeeFirstName(), employee.getEmployeeFirstName());
        //CleanUp
        employeeDbService.deleteEmployee(1L);
    }

}
