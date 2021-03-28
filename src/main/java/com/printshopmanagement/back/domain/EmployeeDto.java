package com.printshopmanagement.back.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
@Getter
@AllArgsConstructor
public class EmployeeDto {

    private Long id;
    private Long employeePersonalNumber;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeeAddress;
    private String employeeStatus;
    private Long employeeSalary;
}
