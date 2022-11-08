package com.example.taco_cloud;

import com.example.taco_cloud.controllers.HomeController;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
@AutoConfigureMockMvc
public class HomeControllerTest {


    private MockMvc mockMvc;

    @Autowired
    public HomeControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }



    @Test
    public void testHomePage() throws Exception {

            mockMvc.perform(get("/"))
                    .andExpectAll(status().isOk(),
                    view().name("home"),
                    content().string(containsString("Welcome to...")));
        }



}
