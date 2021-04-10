package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDbService extends CrudService<Employee, Integer> {
    private final EmployeeRepo employeeRepo;

    public EmployeeDbService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    protected JpaRepository<Employee, Integer> getRepository() {
        return employeeRepo;
    }

    public Employee saveEmployee(final Employee employee) {
        return employeeRepo.save(employee);
    }

    public Optional<Employee> getEmployee(final Integer id) {
        return employeeRepo.findById(id);
    }

    public void deleteEmployee(final Integer id) {
        employeeRepo.deleteById(id);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

}
