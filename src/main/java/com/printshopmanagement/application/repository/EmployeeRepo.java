package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    @Override
    List<Employee> findAll();
}
