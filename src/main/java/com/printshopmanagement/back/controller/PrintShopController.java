package com.printshopmanagement.back.controller;

import com.printshopmanagement.back.domain.*;
import com.printshopmanagement.back.exceptions.EmployeeNotFoundException;
import com.printshopmanagement.back.exceptions.EquipmentNotFoundException;
import com.printshopmanagement.back.exceptions.MaterialNotFoundException;
import com.printshopmanagement.back.mapper.EmployeeMapper;
import com.printshopmanagement.back.mapper.EquipmentMapper;
import com.printshopmanagement.back.mapper.MaterialMapper;
import com.printshopmanagement.back.repository.DbService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class PrintShopController {
    private final DbService dbService;
    private final EmployeeMapper employeeMapper;
    private final MaterialMapper materialMapper;
    private final EquipmentMapper equipmentMapper;

    public PrintShopController(DbService dbService, EmployeeMapper employeeMapper, MaterialMapper materialMapper, EquipmentMapper equipmentMapper) {
        this.dbService = dbService;
        this.employeeMapper = employeeMapper;
        this.materialMapper = materialMapper;
        this.equipmentMapper = equipmentMapper;
    }

    @PostMapping(value = "/addEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto addEmployee(@RequestBody final EmployeeDto employeeDto) {
        var persistentEmployee = dbService.saveEmployee(employeeMapper.mapToEmployee(employeeDto));
        return employeeMapper.mapToEmployeeDto(persistentEmployee);
    }

    @PutMapping(value = "/updateEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) {
        var persistentEmployee = dbService.saveEmployee(employeeMapper.mapToEmployee(employeeDto));
        return employeeMapper.mapToEmployeeDto(persistentEmployee);
    }

    @GetMapping(value = "/getEmployee/{id}")
    public EmployeeDto getEmployee(@PathVariable("id") final Long id) throws EmployeeNotFoundException {
        if(dbService.getEmployee(id).isPresent()){
            return employeeMapper.mapToEmployeeDto(dbService.getEmployee(id).get());
        } else throw new EmployeeNotFoundException();
    }

    @GetMapping(value = "/getEmployees")
    public List<EmployeeDto> getEmployees() {
        return employeeMapper.mapToEmployeeListDto(dbService.getAllEmployees());
    }

    @DeleteMapping(value = "/deleteEmployee")
    public void removeEmployee(@RequestParam("id") Long id) {
        dbService.deleteEmployee(id);
    }

    @PostMapping(value = "/addMaterial", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MaterialDto addMaterial(@RequestBody final MaterialDto materialDto) {
        var persistMaterial = dbService.saveMaterial(materialMapper.mapToMaterial(materialDto));
        return materialMapper.mapToMaterialDto(persistMaterial);
    }

    @PutMapping(value = "/updateMaterial", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MaterialDto updateMaterial(@RequestBody MaterialDto materialDto) {
        var persisMaterial = dbService.saveMaterial(materialMapper.mapToMaterial(materialDto));
        return materialMapper.mapToMaterialDto(persisMaterial);
    }

    @GetMapping(value = "/getMaterial/{id}")
    public MaterialDto getMaterial(@PathVariable("id") final Long id) throws MaterialNotFoundException {
        if(dbService.getMaterial(id).isPresent()){
            return materialMapper.mapToMaterialDto(dbService.getMaterial(id).get());
        } else throw new MaterialNotFoundException();
    }

    @GetMapping(value = "/getMaterials")
    public List<MaterialDto> getMaterials() {
        return materialMapper.mapToMaterialListDto(dbService.getAllMaterials());
    }

    @DeleteMapping(value = "/deleteMaterial")
    public void removeMaterial(@RequestParam("id") final Long id) {
        dbService.deleteMaterial(id);
    }

    @PostMapping(value = "/addEquipment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentDto addEquipment(final EquipmentDto equipmentDto) {
        var persistentEquipment = dbService.saveEquipment(equipmentMapper.mapToEquipment(equipmentDto));
        return equipmentMapper.mapToEquipmentDto(persistentEquipment);
    }

    @GetMapping(value = "/getEquipment/{id}")
    public EquipmentDto getEquipment(@PathVariable("id") final Long id) throws EquipmentNotFoundException {
        if (dbService.getEquipment(id).isPresent()){
            return equipmentMapper.mapToEquipmentDto(dbService.getEquipment(id).get());
        } else throw new EquipmentNotFoundException();
    }

    @PutMapping(value = "/updateEquipment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EquipmentDto updateEquipment(@RequestBody final EquipmentDto equipmentDto) {
        var persistentEquipment = dbService.saveEquipment(equipmentMapper.mapToEquipment(equipmentDto));
        return equipmentMapper.mapToEquipmentDto(persistentEquipment);
    }

    @GetMapping(value = "/getEquipments")
    public List<EquipmentDto> getEquipments() {
        return equipmentMapper.mapToEquipmentListDto(dbService.getAllEquipments());
    }
    @DeleteMapping(value = "/deleteEquipment")
    public void removeEquipment(@RequestParam("id") final Long id) {
        dbService.deleteEquipment(id);
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
