package com.printshopmanagement.application.controller;

import com.google.gson.Gson;
import com.printshopmanagement.application.domain.Material;
import com.printshopmanagement.application.domain.MaterialDto;
import com.printshopmanagement.application.mapper.MaterialMapper;
import com.printshopmanagement.application.repository.MaterialDbService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(MaterialController.class)
public class MaterialControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MaterialDbService materialDbService;

    @MockBean
    private MaterialMapper materialMapper;

    private Gson gson;
    private Material material;
    private MaterialDto materialDto;

    @BeforeEach
    public void setUp() {
        gson = new Gson();
        material = new Material(1L, "TestType", "TestName", 4L, "Comment");
        materialDto = new MaterialDto(1L, "TestType", "TestName", 4L, "Comment");
        when(materialMapper.mapToMaterial(any())).thenReturn(material);
        when(materialMapper.mapToMaterialDto(any())).thenReturn(materialDto);
    }

    @Test
    public void testAddMaterial() throws Exception{
        //Given
        String materialJson = gson.toJson(material);
        when(materialDbService.saveMaterial(material)).thenReturn(material);
        //when&then
        this.mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/addMaterial")
        .characterEncoding("UTF-8")
        .contentType(MediaType.APPLICATION_JSON)
        .content(materialJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.materialName", Matchers.is("TestName")));

    }

    @Test
    public void testGetMaterial() throws Exception{
        //Given
        when(materialDbService.getMaterial(any())).thenReturn(Optional.of(material));
        //When&Then
        this.mockMvc.perform(MockMvcRequestBuilders
        .get("/v1/getMaterial/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.materialName", Matchers.is("TestName")));
    }

    @Test
    public void testGetMaterials() throws Exception{
        //Given
        List<MaterialDto> materialDtoList = new ArrayList<>();
        materialDtoList.add(materialDto);
        materialDtoList.add(materialDto);
        when(materialMapper.mapToMaterialListDto(any())).thenReturn(materialDtoList);
        //when&then
        this.mockMvc.perform(MockMvcRequestBuilders
        .get("/v1/getMaterials"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",Matchers.hasSize(2)));
    }

    @Test
    public void testRemoveMaterial() throws Exception{
        //when&then
        this.mockMvc.perform(MockMvcRequestBuilders
        .delete("/v1/deleteMaterial")
        .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateMarial() throws Exception{
       //given
         Material updatedMaterial = new Material(1L, "TestType", "UpdatedTestName", 4L, "Comment");
        MaterialDto updatedMaterialDto = new MaterialDto(1L, "TestType", "UpdatedTestName", 4L, "Comment");
        when(materialMapper.mapToMaterial(any())).thenReturn(updatedMaterial);
        when(materialMapper.mapToMaterialDto(any())).thenReturn(updatedMaterialDto);
        when(materialDbService.saveMaterial(updatedMaterial)).thenReturn(updatedMaterial);
        String updatedMaterialJson = gson.toJson(updatedMaterial);
        //when&then
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/updateMaterial")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(updatedMaterialJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.materialName", Matchers.is("UpdatedTestName")));
    }


}
