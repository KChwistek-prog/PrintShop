package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskDbService {
    @Autowired
    private TaskRepo taskRepo;

    public Task saveTask(final Task task){
        return taskRepo.save(task);
    }

    public Optional<Task> getTask(final Long id){
        return taskRepo.findById(id);
    }

    public List<Task>getTasks(){
        return taskRepo.findAll();
    }

    public void deleteTask(final Long id){
        taskRepo.deleteById(id);
    }
}
