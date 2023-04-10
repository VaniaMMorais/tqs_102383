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
import java.time.temporal.ChronoUnit;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    private Envmonitor envmonitor;

    @Mock 
    private CacheData cache;

    @Mock
    private EnvmonitorController controller;

    @Mock
    private HTTPClient client;

    @InjectMocks 
    private EnvmonitorService service;
    

    @BeforeEach
    public void setup() {
        client = mock(HTTPClient.class);
        service = new EnvmonitorService();
        service.setHttpClient(client);
    }


    @Test
    public void testGetAirQuality(){
        String cityName = "Lisbon";
        
        // Mocking the httpClient.getLocation(cityName, 1) call
        String locationResponse = "[{\"lat\": 38.7167, \"lon\": -9.1333}]";
        when(client.getLocation(cityName, 1)).thenReturn(locationResponse);
        
        // Mocking the httpClient.getAirQuality(lat, lon, cityName) call
        String airQualityResponse = "{\"list\": [{\"main\": {\"aqi\": 3.0}, \"components\":" +
        " {\"co\": 186.3, \"no2\": 12.67, \"o3\": 51.47, \"so2\": 5.17, \"pm10\": 11.0}}]}";
        when(client.getAirQuality(38.7167, -9.1333, cityName)).thenReturn(airQualityResponse);

        Envmonitor result = service.getEnvmonitorByCityName(cityName);

        CacheData cacheData = new CacheData();
        Envmonitor envmonitor = new Envmonitor();
        envmonitor.setId(1);
        envmonitor.setCityName("Lisbon");
        envmonitor.setDate(LocalDateTime.now());
        envmonitor.setNo2(12.67);
        envmonitor.setSo2(5.17);
        envmonitor.setO3(51.47);
        envmonitor.setCo(186.3);    
        envmonitor.setPm10(11.0);
        cacheData.put("Lisbon", envmonitor);

        // verify(cache).getFromCache(cityName);
        assertEquals(envmonitor.getCo(), result.getCo());
    }


    @Test 
    void testGetNonValidInfoByCountryClass() {
        // Verifica se uma exceção é lançada quando o argumento é vazio
        assertThrows(IllegalArgumentException.class, () -> {
            service.getEnvmonitorByCityName("");
        });

        // Verifica se uma exceção é lançada quando o argumento é nulo
        assertThrows(IllegalArgumentException.class, () -> {
            service.getEnvmonitorByCityName(null);
        });
    }

    @Test
    public void testGetAirQualityFromCache(){
        String cityName = "Lisbon";
        
        // Mocking the httpClient.getLocation(cityName, 1) call
        String locationResponse = "[{\"lat\": 38.7167, \"lon\": -9.1333}]";
        when(client.getLocation(cityName, 1)).thenReturn(locationResponse);
        
        // Mocking the httpClient.getAirQuality(lat, lon, cityName) call
        String airQualityResponse = "{\"list\": [{\"main\": {\"aqi\": 3.0}, \"components\": {\"co\": 186.3, \"no2\": 12.67, \"o3\": 51.47, \"so2\": 5.17, \"pm10\": 11.0}}]}";
        when(client.getAirQuality(38.7167, -9.1333, cityName)).thenReturn(airQualityResponse);
        
        Envmonitor expectedEnvmonitor = new Envmonitor();
        expectedEnvmonitor.setCityName(cityName);
        expectedEnvmonitor.setAqi(3.0);
        expectedEnvmonitor.setCo(186.3);
        expectedEnvmonitor.setNo2(12.67);
        expectedEnvmonitor.setO3(51.47);
        expectedEnvmonitor.setSo2(5.17);
        expectedEnvmonitor.setPm10(11.0);
        expectedEnvmonitor.setDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        
        Envmonitor actualEnvmonitor = service.getEnvmonitorByCityName(cityName);
        assertEquals(expectedEnvmonitor.getO3(), actualEnvmonitor.getO3());
        // foi preciso comparar um parametro em especifico porque o date nao é igual
    }



    
}
