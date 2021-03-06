package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private Timestamp calibrationDate;

    @Column
    private String additionalComments;

}
