package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeDto {

    private Integer id;
    private Long employeePersonalNumber;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeAddress;
    private String employeeStatus;
    private Long employeeSalary;
}
