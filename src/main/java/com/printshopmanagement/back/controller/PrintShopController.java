package com.printshopmanagement.back.controller;

import com.printshopmanagement.back.domain.*;
import com.printshopmanagement.back.exceptions.*;
import com.printshopmanagement.back.mapper.*;
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
    private final TaskMapper taskMapper;

    public PrintShopController(DbService dbService, EmployeeMapper employeeMapper, MaterialMapper materialMapper, EquipmentMapper equipmentMapper, TaskMapper taskMapper) {
        this.dbService = dbService;
        this.employeeMapper = employeeMapper;
        this.materialMapper = materialMapper;
        this.equipmentMapper = equipmentMapper;
        this.taskMapper = taskMapper;
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
        if (dbService.getEmployee(id).isPresent()) {
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
        if (dbService.getMaterial(id).isPresent()) {
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
    public EquipmentDto addEquipment(@RequestBody final EquipmentDto equipmentDto) {
        var persistentEquipment = dbService.saveEquipment(equipmentMapper.mapToEquipment(equipmentDto));
        return equipmentMapper.mapToEquipmentDto(persistentEquipment);
    }

    @GetMapping(value = "/getEquipment/{id}")
    public EquipmentDto getEquipment(@PathVariable("id") final Long id) throws EquipmentNotFoundException {
        if (dbService.getEquipment(id).isPresent()) {
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

    @PutMapping(value = "/addTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto addTask(@RequestBody final TaskDto taskDto) {
        var persistentTask = dbService.saveTask(taskMapper.mapToTask(taskDto));
        return taskMapper.mapToTaskDto(persistentTask);
    }

    @GetMapping(value = "/getTask/{id}")
    public TaskDto getTask(@PathVariable("id") final Long id) throws TaskNotFoundException {
        var persistentTask = dbService.getTask(id);
        if (persistentTask.isPresent()) {
            return taskMapper.mapToTaskDto(persistentTask.get());
        } else throw new TaskNotFoundException();
    }

    @GetMapping(value = "/getTaskList")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(dbService.getTasks());
    }

    @PostMapping(value = "/updateTask")
    public TaskDto updateTask(@RequestBody final TaskDto taskDto) {
        var persistentTask = dbService.saveTask(taskMapper.mapToTask(taskDto));
        return taskMapper.mapToTaskDto(persistentTask);
    }

    @DeleteMapping(value = "/deleteTask")
    public void removeTask(@RequestParam("id") final Long id) {
        dbService.deleteTask(id);
    }
}
