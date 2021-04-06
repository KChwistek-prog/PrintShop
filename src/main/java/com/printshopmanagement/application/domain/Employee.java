package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee{

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Long employeePersonalNumber;

    @Column
    private String employeeFirstName;

    @Column
    private String employeeLastName;

    @Column
    private String employeeAddress;

    @Column
    private String employeeStatus;

    @Column
    private Long employeeSalary;

    public Employee(Long employeePersonalNumber, String employeeFirstName, String employeeLastName, String employeeAddress, String employeeStatus, Long employeeSalary) {
        this.employeePersonalNumber = employeePersonalNumber;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeAddress = employeeAddress;
        this.employeeStatus = employeeStatus;
        this.employeeSalary = employeeSalary;
    }
}
