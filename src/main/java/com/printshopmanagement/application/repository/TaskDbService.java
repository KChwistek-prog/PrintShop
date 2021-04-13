package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskDbService extends CrudService<Task, Integer> {
    private final  TaskRepo taskRepo;

    @Override
    protected JpaRepository<Task, Integer> getRepository() {
        return taskRepo;
    }

    public Task saveTask(final Task task){
        task.setTaskAcceptationDate(Timestamp.valueOf(LocalDateTime.now()));
        task.setTaskRealisationTerm(Timestamp.valueOf(LocalDateTime.now().plusDays(3)));
        return taskRepo.save(task);
    }

    public Optional<Task> getTask(final Integer id){
        return taskRepo.findById(id);
    }

    public List<Task>getTasks(){
        return taskRepo.findAll();
    }

    public List<Task> getDailyTasks(){
        List<Task> daily = new ArrayList<>();
        for (Task task:taskRepo.findAll()) {
            if(task.getTaskAcceptationDate().toLocalDateTime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()){
                daily.add(task);
            }
        }
        return daily;
    }

    public void deleteTask(final Integer id){
        taskRepo.deleteById(id);
    }
}
