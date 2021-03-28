package com.printshopmanagement.back.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String equipmentName;

    @Column
    private String equipmentStatus;

    @Column
    private String calibrationDate;

    @Column
    private String additionalComments;

}
