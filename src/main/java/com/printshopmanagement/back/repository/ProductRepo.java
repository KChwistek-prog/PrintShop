package com.printshopmanagement.back.repository;

import com.printshopmanagement.back.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {
    @Override
    List<Product> findAll();
}
