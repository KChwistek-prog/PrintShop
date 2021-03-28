package com.printshopmanagement.back.controller;

import com.printshopmanagement.back.domain.Employee;
import com.printshopmanagement.back.domain.EmployeeDto;
import com.printshopmanagement.back.exceptions.EmployeeNotFoundException;
import com.printshopmanagement.back.mapper.EmployeeMapper;
import com.printshopmanagement.back.repository.DbService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class PrintShopController {
    private final DbService dbService;
    private final EmployeeMapper employeeMapper;

    public PrintShopController(DbService dbService, EmployeeMapper employeeMapper) {
        this.dbService = dbService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping(value = "/addEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Employee addEmployee(final Employee employee){
        return dbService.saveEmployee(employee);
    }
    @PutMapping(value = "/updateEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto){
        Employee updatedEmployee = dbService.saveEmployee(employeeMapper.mapToEmployee(employeeDto));
        return employeeMapper.mapToEmployeeDto(updatedEmployee);
    }

    @GetMapping(value = "/getEmployee/{id}")
    public Employee getEmployee(@PathVariable("id") final Long id) throws EmployeeNotFoundException {
        Optional<Employee> employee = dbService.getEmployee(id);
        return  employee.orElseThrow(EmployeeNotFoundException::new);
    }

    @GetMapping(value = "/getEmployees")
    public List<EmployeeDto> getEmployees(){
        List<Employee> employees = dbService.getAllEmployees();
        return employeeMapper.mapToEmployeeListDto(employees);
    }

    @DeleteMapping(value = "/deleteEmployee")
    public void removeEmployee(@RequestParam("id") Long id){
        dbService.deleteEmployee(id);
    }

    public void addMaterial(){
    }

    public void getMaterial(){
    }

    public void getMaterials(){
    }

    public void updateMaterial(){
    }

    public void removeMaterial() {
    }

    public void addEquipment(){
    }

    public void getEquipment(){
    }

    public void getEquipments(){
    }

    public void updateEquipment(){
    }

    public void removeEquipment() {
    }

    public void addTask(){
    }

    public void getTask(){
    }

    public void getTasks(){
    }

    public void updateTask(){
    }

    public void removeTask() {
    }

    public void addProduct(){
    }

    public void getProduct(){
    }

    public void getProducts(){
    }

    public void updateProducts(){
    }

    public void removeProduct() {
    }

}
