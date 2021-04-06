package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DbService {

    @Autowired
    private MaterialRepo materialRepo;
    @Autowired
    private EquipmentRepo equipmentRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private TaskRepo taskRepo;



    public Material saveMaterial(final Material material) {
        return materialRepo.save(material);
    }

    public Optional<Material> getMaterial(final Long id) {
        return materialRepo.findById(id);
    }

    public void deleteMaterial(final Long id) {
        materialRepo.deleteById(id);
    }

    public List<Material> getAllMaterials() {
        return materialRepo.findAll();
    }

    public Equipment saveEquipment(final Equipment equipment) {
        return equipmentRepo.save(equipment);
    }

    public Optional<Equipment> getEquipment(final Long id) {
        return equipmentRepo.findById(id);
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepo.findAll();
    }

    public void deleteEquipment(final Long id) {
        equipmentRepo.deleteById(id);
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

    public List<Product>getAllProducts(){
        return productRepo.findAll();
    }

    public Task saveTask(final Task task){
        return taskRepo.save(task);
    }

    public Optional<Task> getTask(final Long id){
        return taskRepo.findById(id);
    }

    public List<Task>getTasks(){
        return taskRepo.findAll();
    }

    public void deleteTask(final Long id){
        taskRepo.deleteById(id);
    }


}
