package com.printshopmanagement.back.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
