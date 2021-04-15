package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDbService {
    private final EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeDbService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee saveEmployee(final Employee employee) {
        return employeeRepo.save(employee);
    }

    public Optional<Employee> getEmployee(final Long id) {
        return employeeRepo.findById(id);
    }

    public void deleteEmployee(final Long id) {
        employeeRepo.deleteById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

}
