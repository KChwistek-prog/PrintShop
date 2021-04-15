package com.printshopmanagement.application.controller;

import com.google.gson.Gson;
import com.printshopmanagement.application.domain.Product;
import com.printshopmanagement.application.mapper.ProductMapper;
import com.printshopmanagement.application.repository.ProductDbService;
import org.hamcrest.Matchers;
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

import static org.mockito.Mockito.when;

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

    @Test
    public void testAddProduct() throws Exception {
        //When
        Product product = new Product(1L, "Test", "test", 23L, 23L);
        Gson gson = new Gson();
        String productJson = gson.toJson(product);

        when(productDbService.saveProduct(product)).thenReturn(product);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/addProduct")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(MockMvcResultMatchers.status().isOk());
               // .andExpect(MockMvcResultMatchers.jsonPath("$.productId", Matchers.is(1)));

    }
}
