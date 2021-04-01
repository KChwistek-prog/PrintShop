package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Material;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MaterialRepo extends CrudRepository<Material, Long> {
    @Override
    List<Material> findAll();
}
