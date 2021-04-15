package com.printshopmanagement.application.controller;

import com.google.gson.Gson;
import com.printshopmanagement.application.domain.Task;
import com.printshopmanagement.application.domain.TaskDto;
import com.printshopmanagement.application.mapper.TaskMapper;
import com.printshopmanagement.application.repository.TaskDbService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskDbService taskDbService;

    @MockBean
    private TaskMapper taskMapper;

    Timestamp tsStart;
    Timestamp tsEnd;

    @Before
    public void setUp() {
        LocalDateTime ldtMinusTwo = LocalDateTime.now().minusDays(2);
        LocalDateTime now = LocalDateTime.now().minusDays(2);
        Timestamp tsEnd = Timestamp.valueOf(ldtMinusTwo);
        Timestamp tsStart = Timestamp.valueOf(now);
    }

    @Test
    public void testAddTask() throws Exception {
        //Given
        Task task = new Task(1L, "Test Task", "Test Type", "Test Status", "Test Comment", tsStart, tsEnd);
        TaskDto taskDto = new TaskDto(1L, "Test Task", "Test Type", "Test Status", "Test Comment", tsStart, tsEnd);
        Gson gson = new Gson();
        String json = gson.toJson(task);
        when(taskDbService.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        //When & Then
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/addTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // .andExpect(MockMvcResultMatchers.jsonPath("$.taskName", Matchers.is("Test Task")));
    }

    @Test
    public void testGetTask() throws Exception {
        //Given
        Task task = new Task(1L, "Test Task", "Test Type", "Test Status", "Test Comment", tsStart, tsEnd);
        TaskDto taskDto = new TaskDto(1L, "Test Task", "Test Type", "Test Status", "Test Comment", tsStart, tsEnd);
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
        Task task = new Task(1L, "Test Task", "Test Type", "Test Status", "Test Comment", tsStart, tsEnd);
        Task task1 = new Task(1L, "Test Task", "Test Type", "Test Status", "Test Comment", tsStart, tsEnd);
        List<Task> tasklist = new ArrayList<>();
        TaskDto taskDto = new TaskDto(1L, "Test Task", "Test Type", "Test Status", "Test Comment", tsStart, tsEnd);
        TaskDto taskDto1 = new TaskDto(1L, "Test Task", "Test Type", "Test Status", "Test Comment", tsStart, tsEnd);
        List<TaskDto> tasklistDto = new ArrayList<>();
        tasklist.add(task);
        tasklist.add(task1);
        tasklistDto.add(taskDto);
        tasklistDto.add(taskDto1);
        when(taskDbService.getTasks()).thenReturn(tasklist);
        when(taskMapper.mapToTaskDtoList(any())).thenReturn(tasklistDto);
        //When&Then
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/getTaskList"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));

    }
}
