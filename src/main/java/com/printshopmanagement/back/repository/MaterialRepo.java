package com.printshopmanagement.back.repository;

import com.printshopmanagement.back.domain.Material;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MaterialRepo extends CrudRepository<Material, Long> {
    @Override
    List<Material> findAll();
}
