package com.printshopmanagement.application.repository

import com.printshopmanagement.application.domain.Employee
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@SpringBootTest
class EmployeeRepositoryTestSuite extends Specification {

    @Autowired
    EmployeeDbService employeeDbService

    def "database service should save employee"() {
        given:
        def john = new Employee(231541521L, "John", "Doe", "Elm Str", "Working", 2500L)
        when:
        employeeDbService.saveEmployee(john)
        then:
        employeeDbService.getEmployee(1L).isPresent()
        employeeDbService.getEmployee(1L).get().employeeFirstName == "John"
    }

    def "should return list of employees"() {
        given:
            def john = new Employee(231541521L, "John", "Doe", "Elm Str", "Working", 2500L)
            def ann = new Employee(231541521L, "Ann", "Doe", "Elm Str", "Working", 2500L)
            employeeDbService.saveEmployee(john)
            employeeDbService.saveEmployee(ann)
        when:
            var result = employeeDbService.getAllEmployees()
        then:
            result.size() == 2
    }

    def "database service should return John"() {
        setup:
        def john = new Employee(231541521L, "John", "Doe", "Elm Str", "Working", 2500L)
        employeeDbService.saveEmployee(john)

        when:
        var result = employeeDbService.getEmployee(1L)
        then:
        result.orElseThrow().getEmployeeFirstName() == "John"
    }

    def "database service should remove employee from db"() {
        given:
            def john = new Employee(231541521L, "John", "Doe", "Elm Str", "Working", 2500L)
            employeeDbService.saveEmployee(john)
        when:
            employeeDbService.deleteEmployee(1L)
        then:
            employeeDbService.getEmployee(1L).isEmpty()
    }
}
