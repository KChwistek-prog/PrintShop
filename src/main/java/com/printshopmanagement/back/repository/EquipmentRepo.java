package com.printshopmanagement.back.repository;

import com.printshopmanagement.back.domain.Equipment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EquipmentRepo extends CrudRepository<Equipment, Long> {
    @Override
    List<Equipment> findAll();
}
