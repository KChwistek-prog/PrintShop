package com.printshopmanagement.application.mapper;

import com.printshopmanagement.application.domain.Equipment;
import com.printshopmanagement.application.domain.EquipmentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentMapper {

    public Equipment mapToEquipment(EquipmentDto equipmentDto) {
        return new Equipment(
                equipmentDto.getId(),
                equipmentDto.getEquipmentName(),
                equipmentDto.getEquipmentStatus(),
                equipmentDto.getCalibrationDate(),
                equipmentDto.getAdditionalComments()
        );
    }

    public EquipmentDto mapToEquipmentDto(Equipment equipment) {
        return new EquipmentDto(
                equipment.getId(),
                equipment.getEquipmentName(),
                equipment.getEquipmentStatus(),
                equipment.getCalibrationDate(),
                equipment.getAdditionalComments()
        );
    }

    public List<EquipmentDto> mapToEquipmentListDto(List<Equipment> equipmentList) {
        return equipmentList.stream()
                .map(this::mapToEquipmentDto)
                .collect(Collectors.toList());
    }
}
