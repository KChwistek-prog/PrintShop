package com.printshopmanagement.application.repository;

import com.printshopmanagement.application.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskDbService {
    private final  TaskRepo taskRepo;

    @Autowired
    public TaskDbService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task saveTask(final Task task){
        task.setTaskAcceptationDate(Timestamp.valueOf(LocalDateTime.now()));
        task.setTaskRealisationTerm(Timestamp.valueOf(LocalDateTime.now().plusDays(3)));
        return taskRepo.save(task);
    }

    public Optional<Task> getTask(final Long id){
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

    public void deleteTask(final Long id){
        taskRepo.deleteById(id);
    }
}
