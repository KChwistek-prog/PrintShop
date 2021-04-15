package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentDbService {
    private final EquipmentRepo equipmentRepo;

    @Autowired
    public EquipmentDbService(EquipmentRepo equipmentRepo) {
        this.equipmentRepo = equipmentRepo;
    }

    public Equipment saveEquipment(final Equipment equipment) {
        return equipmentRepo.save(equipment);
    }

    public Optional<Equipment> getEquipment(final Long id) {
        return equipmentRepo.findById(id);
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepo.findAll();
    }

    public void deleteEquipment(final Long id) {
        equipmentRepo.deleteById(id);
    }

}
