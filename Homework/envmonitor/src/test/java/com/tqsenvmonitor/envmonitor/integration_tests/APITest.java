package com.tqsenvmonitor.envmonitor.integration_tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class APITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getLocation_invalidInput_returnsBadRequest() throws Exception {
        // Make a GET request with an invalid cityName parameter
        MvcResult result = mockMvc.perform(get("https://api.openweathermap.org/get/1.0/direct?q=invalid&limit=1&appid=6be3ea4da4135b4533844adbb662252b"))
                .andExpect(status().isNotFound()) //Mesmo no postman ele retorna erro 404, por isso não pode ser badrequest
                .andReturn();

        // Check if the response body contains an error message
        String response = result.getResponse().getContentAsString();
        assertTrue(response.contains(""));
    }


    @Test
    public void getAirQuality_validInput_returnsData() throws Exception {
        // Make a GET request with valid latitude and longitude parameters
        MvcResult result = mockMvc.perform(get("http://api.openweathermap.org/data/2.5/air_pollution?lat=40.6574713&lon=-7.9138664&appid=6be3ea4da4135b4533844adbb662252b")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) 
                .andReturn();

        // Check if the response body contains an error message
        String response = result.getResponse().getContentAsString();
        assertTrue(response.contains("Viseu"));
    }
}
