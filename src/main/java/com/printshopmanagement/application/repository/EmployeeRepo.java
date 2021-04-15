package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
    @Override
    List<Employee> findAll();
}
