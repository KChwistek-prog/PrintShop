package com.printshopmanagement.application.controller;

import com.printshopmanagement.application.domain.Task;
import com.printshopmanagement.application.domain.TaskDto;
import com.printshopmanagement.application.exceptions.TaskNotFoundException;
import com.printshopmanagement.application.mapper.TaskMapper;
import com.printshopmanagement.application.repository.TaskDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskDbService taskDbService;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(TaskDbService taskDbService, TaskMapper taskMapper) {
        this.taskDbService = taskDbService;
        this.taskMapper = taskMapper;
    }


    @PostMapping(value = "/addTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto addTask(@RequestBody final TaskDto taskDto) {
        var persistentTask = taskDbService.saveTask(taskMapper.mapToTask(taskDto));
        return taskMapper.mapToTaskDto(persistentTask);
    }

    @GetMapping(value = "/getTask/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto getTask(@PathVariable("id") final Long id) throws TaskNotFoundException {
        Optional<Task> task = taskDbService.getTask(id);
        return taskMapper.mapToTaskDto(task.orElseThrow(TaskNotFoundException::new));
    }

    @GetMapping(value = "/getTaskList")
    public List<TaskDto> getTasks() {
        List<Task> tasks = taskDbService.getTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }

    @PutMapping(value = "/updateTask", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskDto updateTask(@RequestBody final TaskDto taskDto) {
        var persistentTask = taskDbService.saveTask(taskMapper.mapToTask(taskDto));
        return taskMapper.mapToTaskDto(persistentTask);
    }

    @DeleteMapping(value = "/deleteTask")
    public void removeTask(@RequestParam("id") final Long id) {
        taskDbService.deleteTask(id);
    }
}
