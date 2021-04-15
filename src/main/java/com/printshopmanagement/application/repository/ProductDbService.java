package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDbService{
    private final ProductRepo productRepo;

    @Autowired
    public ProductDbService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Product saveProduct(final Product product){
        return productRepo.save(product);
    }

    public Optional<Product> getProduct(final Long id){
        return productRepo.findById(id);
    }

    public void deleteProduct(final Long id){
        productRepo.deleteById(id);
    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
}
