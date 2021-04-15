package com.printshopmanagement.application.controller;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@WebMvcTest(MaterialController.class)
@RunWith(SpringRunner.class)
public class MaterialControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;


}
