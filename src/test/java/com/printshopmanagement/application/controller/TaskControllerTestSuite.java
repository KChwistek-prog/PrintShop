package com.printshopmanagement.application.controller;

import com.google.gson.Gson;
import com.printshopmanagement.application.domain.Task;
import com.printshopmanagement.application.domain.TaskDto;
import com.printshopmanagement.application.repository.TaskDbService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskDbService taskDbService;

    @Test
    public void testAddTask() throws Exception {
        //Given
        Gson gson = new Gson();
        Task task = new Task(1, "Test Task", "Test Type", "Test Status", "Test Comment", new Timestamp(1094722121), new Timestamp(1094722121));
        String json = gson.toJson(task);
        when(taskDbService.saveTask(any())).thenReturn(task);
        //When & Then
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/addTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
