package com.printshopmanagement.back.repository;

import com.printshopmanagement.back.domain.Employee;
import com.printshopmanagement.back.domain.Material;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DbService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private MaterialRepo materialRepo;



    public Employee saveEmployee(final Employee employee){
        return employeeRepo.save(employee);
    }

    public Optional<Employee> getEmployee(final Long id){
        return employeeRepo.findById(id);
    }

    public void deleteEmployee(final Long id){
        employeeRepo.deleteById(id);
    }

    public List<Employee> getAllEmployees(){
        return employeeRepo.findAll();
    }

    public Material saveMaterial(final Material material){
        return materialRepo.save(material);
    }

    public Optional<Material> getMaterial(final Long id){
        return materialRepo.findById(id);
    }

    public void deleteMaterial(final Long id){
        materialRepo.deleteById(id);
    }

    public List<Material> getAllMaterials(){
        return materialRepo.findAll();
    }

}
