package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {
    List<Product> findAll();
}
