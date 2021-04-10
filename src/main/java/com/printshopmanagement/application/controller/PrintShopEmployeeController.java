package com.printshopmanagement.application.controller;

import com.printshopmanagement.application.domain.EmployeeDto;
import com.printshopmanagement.application.exceptions.EmployeeNotFoundException;
import com.printshopmanagement.application.mapper.EmployeeMapper;
import com.printshopmanagement.application.repository.EmployeeDbService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class PrintShopEmployeeController {
    private EmployeeDbService employeeDbService;
    private EmployeeMapper employeeMapper;

    public PrintShopEmployeeController(EmployeeDbService employeeDbService, EmployeeMapper employeeMapper) {
        this.employeeDbService = employeeDbService;
        this.employeeMapper = employeeMapper;
    }

    @PostMapping(value = "/addEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto addEmployee(@RequestBody final EmployeeDto employeeDto) {
        var persistentEmployee = employeeDbService.saveEmployee(employeeMapper.mapToEmployee(employeeDto));
        return employeeMapper.mapToEmployeeDto(persistentEmployee);
    }

    @PutMapping(value = "/updateEmployee", consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmployeeDto updateEmployee(@RequestBody EmployeeDto employeeDto) {
        var persistentEmployee = employeeDbService.saveEmployee(employeeMapper.mapToEmployee(employeeDto));
        return employeeMapper.mapToEmployeeDto(persistentEmployee);
    }

    @GetMapping(value = "/getEmployee/{id}")
    public EmployeeDto getEmployee(@PathVariable("id") final Integer id) throws EmployeeNotFoundException {
        if (employeeDbService.getEmployee(id).isPresent()) {
            return employeeMapper.mapToEmployeeDto(employeeDbService.getEmployee(id).get());
        } else throw new EmployeeNotFoundException();
    }

    @GetMapping(value = "/getEmployees")
    public List<EmployeeDto> getEmployees() {
        return employeeMapper.mapToEmployeeListDto(employeeDbService.getAllEmployees());
    }

    @DeleteMapping(value = "/deleteEmployee")
    public void removeEmployee(@RequestParam("id") Integer id) {
        employeeDbService.deleteEmployee(id);
    }
}
