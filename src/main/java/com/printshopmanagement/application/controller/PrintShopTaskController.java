package com.printshopmanagement.application.controller;

import com.printshopmanagement.application.domain.*;
import com.printshopmanagement.application.exceptions.*;
import com.printshopmanagement.application.mapper.*;
import com.printshopmanagement.application.repository.TaskDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class PrintShopTaskController {

    private final TaskDbService taskDbService;
    private final TaskMapper taskMapper;

    public PrintShopTaskController(TaskDbService taskDbService, TaskMapper taskMapper) {
        this.taskDbService = taskDbService;
        this.taskMapper = taskMapper;
    }

    @PutMapping(value = "/addTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto addTask(@RequestBody final TaskDto taskDto) {
        var persistentTask = taskDbService.saveTask(taskMapper.mapToTask(taskDto));
        return taskMapper.mapToTaskDto(persistentTask);
    }

    @GetMapping(value = "/getTask/{id}")
    public TaskDto getTask(@PathVariable("id") final Integer id) throws TaskNotFoundException {
        var persistentTask = taskDbService.getTask(id);
        if (persistentTask.isPresent()) {
            return taskMapper.mapToTaskDto(persistentTask.get());
        } else throw new TaskNotFoundException();
    }

    @GetMapping(value = "/getTaskList")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(taskDbService.getTasks());
    }

    @PostMapping(value = "/updateTask")
    public TaskDto updateTask(@RequestBody final TaskDto taskDto) {
        var persistentTask = taskDbService.saveTask(taskMapper.mapToTask(taskDto));
        return taskMapper.mapToTaskDto(persistentTask);
    }

    @DeleteMapping(value = "/deleteTask")
    public void removeTask(@RequestParam("id") final Integer id) {
        taskDbService.deleteTask(id);
    }
}
