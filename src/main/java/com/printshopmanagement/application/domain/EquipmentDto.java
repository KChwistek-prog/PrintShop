package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentDto {
    private Long id;
    private String equipmentName;
    private String equipmentStatus;
    private Timestamp calibrationDate;
    private String additionalComments;
}
