package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class EquipmentDto {
    private final Long id;
    private final String equipmentName;
    private final String equipmentStatus;
    private final Timestamp calibrationDate;
    private final String additionalComments;
}
