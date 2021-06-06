package com.printshopmanagement.application.repository

import com.printshopmanagement.application.domain.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class EmployeeRepositoryTestSuite extends Specification{

    @Autowired
    EmployeeDbService employeeDbService

    Employee employee

    def setup(){
        employee = new Employee(1L, 231541521L, "John", "Doe", "Elm Str", "Working", 2500L)
    }

    def "database service should save employee"(){
        when:
        employeeDbService.saveEmployee(employee)
        then:
        employeeDbService.getEmployee(1L).isPresent()
    }
}
