package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Integer> {
    @Override
    List<Task> findAll();
}
