package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private  Long id;
    private  Long employeePersonalNumber;
    private  String employeeFirstName;
    private  String employeeLastName;
    private  String employeeAddress;
    private  String employeeStatus;
    private  Long employeeSalary;
}
