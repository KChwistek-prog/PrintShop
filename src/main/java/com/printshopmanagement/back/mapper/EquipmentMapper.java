package com.printshopmanagement.back.mapper;

import com.printshopmanagement.back.domain.Equipment;
import com.printshopmanagement.back.domain.EquipmentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipmentMapper {

    public Equipment mapToEquipment(EquipmentDto equipmentDto){
        return new Equipment(
                equipmentDto.getId(),
                equipmentDto.getEquipmentName(),
                equipmentDto.getEquipmentStatus(),
                equipmentDto.getCalibrationDate(),
                equipmentDto.getAdditionalComments()
        );
    }

    public EquipmentDto mapToEquipmentDto(Equipment equipment){
        return new EquipmentDto(
                equipment.getId(),
                equipment.getEquipmentName(),
                equipment.getEquipmentStatus(),
                equipment.getCalibrationDate(),
                equipment.getAdditionalComments()
        );
    }

    public List<EquipmentDto> mapToEquipmentListDto(List<Equipment>equipmentList){
        return equipmentList.stream()
                .map(this::mapToEquipmentDto)
                .collect(Collectors.toList());
    }
}
