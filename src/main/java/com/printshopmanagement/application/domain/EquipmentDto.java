package com.printshopmanagement.application.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EquipmentDto {
    private Integer id;
    private String equipmentName;
    private String equipmentStatus;
    private String calibrationDate;
    private String additionalComments;
}