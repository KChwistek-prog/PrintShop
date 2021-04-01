package com.printshopmanagement.application.mapper;

import com.printshopmanagement.application.domain.Employee;
import com.printshopmanagement.application.domain.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeMapper {

    public Employee mapToEmployee(final EmployeeDto employeeDto){
        return new Employee(
                employeeDto.getId(),
                employeeDto.getEmployeePersonalNumber(),
                employeeDto.getEmployeeFirstName(),
                employeeDto.getEmployeeLastName(),
                employeeDto.getEmployeeAddress(),
                employeeDto.getEmployeeStatus(),
                employeeDto.getEmployeeSalary()
                );
    }

    public EmployeeDto mapToEmployeeDto(final Employee employee){
        return new EmployeeDto(
                employee.getId(),
                employee.getEmployeePersonalNumber(),
                employee.getEmployeeFirstName(),
                employee.getEmployeeLastName(),
                employee.getEmployeeAddress(),
                employee.getEmployeeStatus(),
                employee.getEmployeeSalary()
        );
    }

    public List<EmployeeDto> mapToEmployeeListDto(final List<Employee> employees){
        return employees.stream()
                .map(this::mapToEmployeeDto)
                .collect(Collectors.toList());
    }
}
