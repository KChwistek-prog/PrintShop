package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmployeeDto {

    private final Long id;
    private final Long employeePersonalNumber;
    private final String employeeFirstName;
    private final String employeeLastName;
    private final String employeeAddress;
    private final String employeeStatus;
    private final Long employeeSalary;
}
