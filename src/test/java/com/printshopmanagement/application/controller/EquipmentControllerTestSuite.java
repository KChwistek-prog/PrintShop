package com.printshopmanagement.application.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@WebMvcTest(EquipmentController.class)
@RunWith(SpringRunner.class)
public class EquipmentControllerTestSuite {

    @Test
    public void testGetEquipmentList(){

    }
}
