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

    // @Test
    // public void getLocation_invalidInput() throws Exception {
    //     MvcResult mvcResult = mockMvc.perform(get("/airquality/Viseu5"))
    //             .andExpect(status().isBadRequest())
    //             .andReturn();

    //     String content = mvcResult.getResponse().getContentAsString();
    //     assertTrue(content.contains("Invalid input"));
    // }


    @Test
    public void getAirQuality_validInput_returnsData() throws Exception {

        //é dificil testar o conteudo do json porque o valor da aqi varia consoante a hora
        MvcResult mvcResult = mockMvc.perform(get("/airquality/Lisbon"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cityName").value("Lisbon"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        assertFalse(content.isEmpty());
    }
}
