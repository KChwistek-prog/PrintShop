package com.printshopmanagement.application.controller;

import com.google.gson.Gson;
import com.printshopmanagement.application.domain.Product;
import com.printshopmanagement.application.domain.ProductDto;
import com.printshopmanagement.application.mapper.ProductMapper;
import com.printshopmanagement.application.repository.ProductDbService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.util.LambdaSafe;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductDbService productDbService;

    @MockBean
    private ProductMapper productMapper;

    private Product product;
    private ProductDto productDto;
    private Gson gson;

    @Before
    public void setUp() {
        gson = new Gson();
        product = new Product(1L, "Test", "TestProduct", 23L, 23L);
        productDto = new ProductDto(1L, "Test", "TestProduct", 23L, 23L);
        when(productMapper.mapToProduct(any())).thenReturn(product);
        when(productMapper.mapToProductDto(any())).thenReturn(productDto);
    }

    @Test
    public void testAddProduct() throws Exception {
        //When&Then
        String productJson = gson.toJson(product);
        when(productDbService.saveProduct(product)).thenReturn(product);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/addProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProduct() throws Exception {
        //When&Then
        when(productDbService.getProduct(any())).thenReturn(Optional.of(product));
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/getProduct/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", Matchers.is("TestProduct")));
    }

    @Test
    public void testGetProducts() throws Exception {
        //Given
        List<ProductDto> productDtoList = new ArrayList<>();
        productDtoList.add(productDto);
        productDtoList.add(productDto);
        when(productMapper.mapToProductDtoList(any())).thenReturn(productDtoList);
        //When&Then
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/getProducts"))
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        //When&Then
        this.mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/deleteProduct")
                .param("id", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateProduct() throws Exception {
        //Given
        Product updatedProduct = new Product(1L, "Test", "UpdatedProduct", 23L, 23L);
        ProductDto updatedProductDto = new ProductDto(1L, "Test", "UpdatedProduct", 23L, 23L);
        String updatedProductJson = gson.toJson(updatedProduct);
        //When
        when(productMapper.mapToProduct(any())).thenReturn(updatedProduct);
        when(productMapper.mapToProductDto(any())).thenReturn(updatedProductDto);
        when(productDbService.saveProduct(updatedProduct)).thenReturn(updatedProduct);
        //Then
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/updateProduct")
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProductJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", Matchers.is("UpdatedProduct")));
    }
}
