package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task,Long> {
    @Override
    List<Task> findAll();
}
