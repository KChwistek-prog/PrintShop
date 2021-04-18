package com.printshopmanagement.application.controller;

import com.google.gson.Gson;
import com.printshopmanagement.application.domain.Equipment;
import com.printshopmanagement.application.domain.EquipmentDto;
import com.printshopmanagement.application.mapper.EquipmentMapper;
import com.printshopmanagement.application.repository.EquipmentDbService;
import com.printshopmanagement.application.repository.EquipmentRepo;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(EquipmentController.class)
@RunWith(SpringRunner.class)
public class EquipmentControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipmentDbService equipmentDbService;

    @MockBean
    private EquipmentMapper equipmentMapper;

    private Equipment equipment;
    private EquipmentDto equipmentDto;
    private Gson gson;
    private Timestamp date;

    @Before
    public void setUp() {
        gson = new Gson();
        equipment = new Equipment(1L, "TestName", "TestStatus", date, "comment");
        equipmentDto = new EquipmentDto(1L, "TestName", "TestStatus", date, "comment");
        when(equipmentMapper.mapToEquipment(any())).thenReturn(equipment);
        when(equipmentMapper.mapToEquipmentDto(any())).thenReturn(equipmentDto);
    }

    @Test
    public void testGetEquipment() throws Exception {
        //When&Then
        when(equipmentDbService.getEquipment(any())).thenReturn(Optional.of(equipment));
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/getEquipment/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.equipmentName", Matchers.is("TestName")));
    }

    @Test
    public void testGetEquipments() throws Exception {
        //Given
        List<EquipmentDto> equipmentListDto = new ArrayList<>();
        equipmentListDto.add(equipmentDto);
        equipmentListDto.add(equipmentDto);
        when(equipmentMapper.mapToEquipmentListDto(any())).thenReturn(equipmentListDto);
        //When&Then
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/getEquipments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void testAddEquipment() throws Exception {
        //When&Then
        String equipmentJson = gson.toJson(equipment);
        when(equipmentDbService.saveEquipment(equipment)).thenReturn(equipment);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/addEquipment")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(equipmentJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteEquipment() throws Exception {
        //When&Then
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/deleteEquipment")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateEquipment() throws Exception {
        //Given
        Equipment updatedEquipment = new Equipment(1L, "UpdatedTestName", "TestStatus", date, "comment");
        EquipmentDto updatedEquipmentDto = new EquipmentDto(1L, "UpdatedTestName", "TestStatus", date, "comment");
        String updatedEq = gson.toJson(updatedEquipment);
        when(equipmentMapper.mapToEquipment(any())).thenReturn(updatedEquipment);
        when(equipmentMapper.mapToEquipmentDto(any())).thenReturn(updatedEquipmentDto);
        when(equipmentDbService.saveEquipment(any())).thenReturn(updatedEquipment);

        //When&Then
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/updateEquipment")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(updatedEq))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.equipmentName", Matchers.is("UpdatedTestName")));
    }

}
