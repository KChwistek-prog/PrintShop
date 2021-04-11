package com.printshopmanagement.application.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

@AutoConfigureMockMvc
@WebMvcTest
@SpringBootTest
class TaskControllerTestSuite extends Specification {
    @Autowired
    private MockMvc mvc

//    def "when get is performed then the response status is 200"(){
//        expect:"status is OK"
//    }

}
