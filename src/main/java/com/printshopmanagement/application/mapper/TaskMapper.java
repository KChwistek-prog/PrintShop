package com.printshopmanagement.application.mapper;

import com.printshopmanagement.application.domain.Task;
import com.printshopmanagement.application.domain.TaskDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskMapper {
    public TaskDto mapToTaskDto(Task task){
        return new TaskDto(
                task.getId(),
                task.getTaskName(),
                task.getTaskType(),
                task.getTaskStatus(),
                task.getTaskComment()
        );
    }

    public Task mapToTask(TaskDto taskDto){
        return new Task(
                taskDto.getId(),
                taskDto.getTaskName(),
                taskDto.getTaskType(),
                taskDto.getTaskStatus(),
                taskDto.getTaskComment()
        );
    }

    public List<TaskDto> mapToTaskDtoList(List<Task> taskList){
        return taskList.stream()
                .map(this::mapToTaskDto)
                .collect(Collectors.toList());
    }
}
