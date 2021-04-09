package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepo extends JpaRepository<Equipment, Integer> {
    List<Equipment> findAll();
}
