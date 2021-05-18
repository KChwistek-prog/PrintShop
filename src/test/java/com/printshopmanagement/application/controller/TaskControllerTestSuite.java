package com.printshopmanagement.application.controller;

import com.google.gson.Gson;
import com.printshopmanagement.application.domain.Task;
import com.printshopmanagement.application.domain.TaskDto;
import com.printshopmanagement.application.mapper.TaskMapper;
import com.printshopmanagement.application.repository.TaskDbService;
import com.printshopmanagement.application.repository.TaskRepo;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskDbService taskDbService;

    @MockBean
    private TaskMapper taskMapper;

    @MockBean
    private TaskRepo taskRepo;

    private Gson gson;
    private Timestamp tsStart;
    private Timestamp tsEnd;
    private Task task;
    private TaskDto taskDto;

    @BeforeEach
    public void setUp() {
        gson = new Gson();
        task = new Task(1L, "Test Task", "Test Type", "Test Status", "Test Comment", tsStart, tsEnd);
        taskDto = new TaskDto(1L, "Test Task", "Test Type", "Test Status", "Test Comment", tsStart, tsEnd);
        when(taskMapper.mapToTaskDto(any())).thenReturn(taskDto);
        when(taskMapper.mapToTask(any())).thenReturn(task);
    }

    @Test
    public void testAddTask() throws Exception {
        //Given
        String json = gson.toJson(task);
        when(taskDbService.saveTask(task)).thenReturn(task);
        //When & Then
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/addTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetTask() throws Exception {
        //Given
        when(taskDbService.getTask(any())).thenReturn(Optional.of(task));
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        //When&Then
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/getTask/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.taskName", Matchers.is("Test Task")));
    }

    @Test
    public void testGetTasks() throws Exception {
        //Given
        List<TaskDto> tasklistDto = new ArrayList<>();
        tasklistDto.add(taskDto);
        tasklistDto.add(taskDto);
        when(taskMapper.mapToTaskDtoList(any())).thenReturn(tasklistDto);
        //When&Then
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/getTaskList"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }

    @Test
    public void testUpdateTask() throws Exception {
        //Given
        Task updatedtask = new Task(1L, "Test Updated Task", "Test Type", "Test Status", "Test Comment", tsStart, tsEnd);
        TaskDto updatedtaskDto = new TaskDto(1L, "Test Updated Task", "Test Type", "Test Status", "Test Comment", tsStart, tsEnd);
        String updatedTask = gson.toJson(updatedtask);
        when(taskMapper.mapToTaskDto(any())).thenReturn(updatedtaskDto);
        when(taskMapper.mapToTask(any())).thenReturn(updatedtask);
        when(taskDbService.saveTask(any())).thenReturn(updatedtask);
        //When&Then
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(updatedTask))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.taskName", Matchers.is("Test Updated Task")));

    }

    @Test
    public void testDeleteTask() throws Exception {
        //When and Then
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/deleteTask")
                .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
