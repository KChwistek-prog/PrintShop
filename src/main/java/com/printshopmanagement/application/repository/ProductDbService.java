package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDbService extends CrudService<Product, Integer> {
    private final ProductRepo productRepo;

    public ProductDbService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    protected JpaRepository<Product, Integer> getRepository() {
        return productRepo;
    }
    public Product saveProduct(final Product product){
        return productRepo.save(product);
    }

    public Optional<Product> getProduct(final Integer id){
        return productRepo.findById(id);
    }

    public void deleteProduct(final Integer id){
        productRepo.deleteById(id);
    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }
}
