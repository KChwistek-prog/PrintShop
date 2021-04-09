package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    List<Product> findAll();
}
