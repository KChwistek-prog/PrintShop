package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Equipment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EquipmentRepo extends CrudRepository<Equipment, Long> {
    List<Equipment> findAll();
}
