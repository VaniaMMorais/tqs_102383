package com.lab3.lab3_2;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.lab3.lab3_2.Car;
import com.lab3.lab3_2.CarManagerService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@WebMvcTest
public class CarControllerMockTest {
    @Autowired
    private MockMvc mvcClient;

    @MockBean
    private CarManagerService carManagerService;

    @Test
    public void testCarCreate() throws IOException, Exception {
        Car c1 = new Car("car1", "m1");
        when(carManagerService.save(any())).thenReturn(c1);

        mvcClient.perform(
        post("/api/car")
            .contentType(MediaType.APPLICATION_JSON)
            .content(JsonUtils.toJson(c1)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.model", is("m1")));

        verify(carManagerService, times(1)).save(any());
    }

    @Test
    public void testReturnArrayForManyCars() throws Exception {
        Car c1 = new Car("car1", "m1");
        Car c2 = new Car("car2", "m2");
        Car c3 = new Car("car3", "m3");
        Car c4 = new Car("car4", "m4");

        List<Car> allCars = Arrays.asList(c1, c2, c3, c4);

        when(carManagerService.getAllCars()).thenReturn(allCars);

        mvcClient.perform(
        get("/api/allCars").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(4)))
        .andExpect(jsonPath("$[0].model", is("m1")))
        .andExpect(jsonPath("$[1].model", is("m2")))
        .andExpect(jsonPath("$[2].model", is("m3")))
        .andExpect(jsonPath("$[3].model", is("m4")));

        verify(carManagerService, times(1)).getAllCars();
    }

    @Test
    public void testReturnCarDetailsIfCarExists() throws Exception {
        Car c1 = new Car("car1", "m1");

        when(carManagerService.getCarDetails(anyLong())).thenReturn(Optional.empty());

        mvcClient.perform(
            get("/api/car/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.model", is("m1")));
    
        verify(carManagerService, times(1)).getCarDetails(anyLong());
    }

    @Test
    public void testReturnForInvalidCar() throws Exception {
        when(carManagerService.getCarDetails(anyLong())).thenReturn(Optional.empty());

        mvcClient.perform(get("/api/car/25"))
        .andExpect(status().isNotFound());

        verify(carManagerService, times(1)).getCarDetails(anyLong());
    }
    
}
