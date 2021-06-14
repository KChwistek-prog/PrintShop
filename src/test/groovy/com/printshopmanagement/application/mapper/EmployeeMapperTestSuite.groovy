package com.printshopmanagement.application.mapper

import com.printshopmanagement.application.domain.Employee
import com.printshopmanagement.application.domain.EmployeeDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class EmployeeMapperTestSuite extends Specification {
    @Autowired
    private EmployeeMapper employeeMapper

    private def john = new Employee()
    private def johnDto = new EmployeeDto()


    def setup(){
        john = new Employee(1L,231541521L, "John", "Doe", "Elm Str", "Working", 2500L)
        johnDto = new EmployeeDto(1L, 231541521L, "John", "Doe", "Elm Str", "Working", 2500L)
    }

    def "should map employee to employee DTO"(){
        when:
            var result = employeeMapper.mapToEmployeeDto(john)
        then:
            result.getEmployeeFirstName() == johnDto.getEmployeeFirstName()
    }

    def "should map employee dto to employee"(){
        when:
            var result = employeeMapper.mapToEmployee(johnDto)
        then:
            result.employeeFirstName == john.getEmployeeFirstName()
    }

    def "should map employee list to employee dto list"(){
        given:
            List<Employee> employeeList = new ArrayList<>()
            employeeList.add(john)
        when:
            var result = employeeMapper.mapToEmployeeListDto(employeeList)
        then:
            result.get(0).getEmployeeFirstName() == john.getEmployeeFirstName()
    }
}
