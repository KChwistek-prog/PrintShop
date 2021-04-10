package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentDbService extends CrudService<Equipment, Integer> {
    private final EquipmentRepo equipmentRepo;

    public EquipmentDbService(EquipmentRepo equipmentRepo) {
        this.equipmentRepo = equipmentRepo;
    }

    public Equipment saveEquipment(final Equipment equipment) {
        return equipmentRepo.save(equipment);
    }

    public Optional<Equipment> getEquipment(final Integer id) {
        return equipmentRepo.findById(id);
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepo.findAll();
    }

    public void deleteEquipment(final Integer id) {
        equipmentRepo.deleteById(id);
    }

    @Override
    protected JpaRepository<Equipment, Integer> getRepository() {
        return equipmentRepo;
    }
}
