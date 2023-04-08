package com.tqsenvmonitor.envmonitor.service_level_tests;

import com.tqsenvmonitor.envmonitor.cache.CacheData;
import com.tqsenvmonitor.envmonitor.model.Envmonitor;
import com.tqsenvmonitor.envmonitor.service.EnvmonitorService;
import com.tqsenvmonitor.envmonitor.connection.HTTPClient;
import com.tqsenvmonitor.envmonitor.controller.EnvmonitorController;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    private Envmonitor envmonitor;

    @Mock private CacheData cache;

    @Mock private EnvmonitorService service;
    
    @InjectMocks private EnvmonitorController envmonitorController;

    @BeforeEach
    void setUp(){
        envmonitor = new Envmonitor();
        envmonitor.setCityName("Viseu");
        envmonitor.setAqi(1.0);
        envmonitor.setCo(1.0);
        envmonitor.setNo2(1.0);
        envmonitor.setO3(1.0);
        envmonitor.setPm10(1.0);
        envmonitor.setSo2(1.0);
        envmonitor.setDate(LocalDateTime.now());
    }


    // @Test
    // public void testGetAirQuality(){
    //     when(cache.getFromCache("Viseu")).thenReturn(this.envmonitor);
    //     Double lat = 40.6574713;
    //     Double lon = -7.9138664;
    //     Envmonitor result = service.getEnvmonitorByCityName("Viseu");

    //     verify(cache).getFromCache("Viseu");
    //     assertEquals(this.envmonitor, result);
    // }


    // @Test 
    // void testGetNonValidInfoByCountryClass() {
    //     when(cache.getFromCache("Viseu2")).thenReturn(null);

    //     Envmonitor envmonitor = service.getEnvmonitorByCityName("Viseu2");

    //     assertNull(envmonitor);

    // }

    @Test
    public void testGetAirQualityFromCache() {
        String city = "Viseu";
        when(cache.getFromCache(city)).thenReturn(envmonitor);
        Envmonitor result = envmonitorController.getAirQuality(city);
        assertEquals(envmonitor, result);
    }

    // @Test
    // public void testGetAirQualityFromService() {
    //     String city = "Viseu";
    //     when(cache.getFromCache(city)).thenReturn(null);
    //     when(service.getEnvmonitorByCityName(city)).thenReturn(envmonitor);
    //     ResponseEntity<Envmonitor> response = envmonitorController.getAirQuality(city);
    //     assertEquals(HttpStatus.OK, response.getStatusCode());
    //     assertEquals(envmonitor, response.getBody());
    // }
}
