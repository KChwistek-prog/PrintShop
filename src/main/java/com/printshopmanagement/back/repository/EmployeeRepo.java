package com.printshopmanagement.back.repository;

import com.printshopmanagement.back.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {
    @Override
    List<Employee> findAll();
}
