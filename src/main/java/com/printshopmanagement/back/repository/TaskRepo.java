package com.printshopmanagement.back.repository;

import com.printshopmanagement.back.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task,Long> {
    @Override
    List<Task> findAll();
}
