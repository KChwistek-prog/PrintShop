package com.printshopmanagement.back.controller;

import com.printshopmanagement.back.domain.Employee;
import com.printshopmanagement.back.domain.EmployeeDto;
import com.printshopmanagement.back.domain.Material;
import com.printshopmanagement.back.domain.MaterialDto;
import com.printshopmanagement.back.exceptions.EmployeeNotFoundException;
import com.printshopmanagement.back.exceptions.MaterialNotFoundException;
import com.printshopmanagement.back.mapper.EmployeeMapper;
import com.printshopmanagement.back.mapper.MaterialMapper;
import com.printshopmanagement.back.repository.DbService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class PrintShopController {
    private final DbService dbService;
    private final EmployeeMapper employeeMapper;
    private final MaterialMapper materialMapper;

    public PrintShopController(DbService dbService, EmployeeMapper employeeMapper, MaterialMapper materialMapper) {
        this.dbService = dbService;
        this.employeeMapper = employeeMapper;
        this.materialMapper = materialMapper;
    }

    @PostMapping(value = "/addEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto addEmployee(@RequestBody final EmployeeDto employeeDto) {
        dbService.saveEmployee(employeeMapper.mapToEmployee(employeeDto));
        return employeeDto;
    }

    @PutMapping(value = "/updateEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) {
        dbService.saveEmployee(employeeMapper.mapToEmployee(employeeDto));
        return employeeDto;
    }

    @GetMapping(value = "/getEmployee/{id}")
    public EmployeeDto getEmployee(@PathVariable("id") final Long id) throws EmployeeNotFoundException {
        if(dbService.getEmployee(id).isPresent()){
            return employeeMapper.mapToEmployeeDto(dbService.getEmployee(id).get());
        } else throw new EmployeeNotFoundException();
    }

    @GetMapping(value = "/getEmployees")
    public List<EmployeeDto> getEmployees() {
        List<Employee> employees = dbService.getAllEmployees();
        return employeeMapper.mapToEmployeeListDto(employees);
    }

    @DeleteMapping(value = "/deleteEmployee")
    public void removeEmployee(@RequestParam("id") Long id) {
        dbService.deleteEmployee(id);
    }

    @PostMapping(value = "/addMaterial", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MaterialDto addMaterial(@RequestBody MaterialDto materialDto) {
        dbService.saveMaterial(materialMapper.mapToMaterial(materialDto));
        return materialDto;
    }

    @PutMapping(value = "/updateMaterial", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MaterialDto updateMaterial(@RequestBody MaterialDto materialDto) {
        dbService.saveMaterial(materialMapper.mapToMaterial(materialDto));
        return materialDto;
    }

    @GetMapping(value = "/getMaterial/{id}")
    public MaterialDto getMaterial(@PathVariable("id") final Long id) throws MaterialNotFoundException {
        if(dbService.getMaterial(id).isPresent()){
            return materialMapper.mapToMaterialDto(dbService.getMaterial(id).get());
        } else throw new MaterialNotFoundException();
    }

    @GetMapping(value = "/getMaterials")
    public List<MaterialDto> getMaterials() {
        List<Material> materials = dbService.getAllMaterials();
        return materialMapper.mapToMaterialListDto(materials);
    }

    @DeleteMapping(value = "/deleteMaterial")
    public void removeMaterial(@RequestParam("id") Long id) {
        dbService.deleteMaterial(id);
    }

    public void addEquipment() {
    }

    public void getEquipment() {
    }

    public void getEquipments() {
    }

    public void updateEquipment() {
    }

    public void removeEquipment() {
    }

    public void addTask() {
    }

    public void getTask() {
    }

    public void getTasks() {
    }

    public void updateTask() {
    }

    public void removeTask() {
    }

    public void addProduct() {
    }

    public void getProduct() {
    }

    public void getProducts() {
    }

    public void updateProducts() {
    }

    public void removeProduct() {
    }

}
