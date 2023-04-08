package com.tqsenvmonitor.envmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.tqsenvmonitor.envmonitor.model.Envmonitor;
import com.tqsenvmonitor.envmonitor.connection.HTTPClient;

@Service
public class EnvmonitorService {
    @Autowired
    private HTTPClient httpClient;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // public List<Envmonitor> getAllEnvmonitors() {
    //     return envmonitorRepository.findAll();
    // }

    // public Envmonitor getEnvmonitorById(long id) {
    //     return envmonitorRepository.findById(id).get();
    // }

    public Envmonitor getEnvmonitorByCityName(String cityName) {
        String response = httpClient.getLocation(cityName, 1);
        List<Double> coords = settingCoords(response);

        String response2 = httpClient.getAirQuality(coords.get(0), coords.get(1), cityName);
        List<Double> airQuality = settingAirData(response2);

        Envmonitor envmonitor = new Envmonitor();
        envmonitor.setCityName(cityName);
        envmonitor.setAqi(airQuality.get(0));
        envmonitor.setCo(airQuality.get(1));
        envmonitor.setNo2(airQuality.get(2));
        envmonitor.setO3(airQuality.get(3));
        envmonitor.setSo2(airQuality.get(4));
        envmonitor.setPm10(airQuality.get(5));
        envmonitor.setDate(LocalDateTime.now());
        System.out.println(envmonitor.toJson());

        return envmonitor;
    }

    public List<Envmonitor> getFutureEnvmonitorByCityName(String cityName){
        String response = httpClient.getLocation(cityName, 1);
        List<Double> coords = settingCoords(response);

        List<Envmonitor> envmonitors = new ArrayList<Envmonitor>();

        String response2 = httpClient.getFutureAirQuality(coords.get(0), coords.get(1), cityName);
        List<List<Object>> airQuality = settingFutureAirData(response2);
        for(List<Object> list : airQuality){
            Envmonitor envmonitor = new Envmonitor();
            envmonitor.setCityName(cityName);
            envmonitor.setAqi(Double.parseDouble(list.get(0).toString()));
            envmonitor.setCo(Double.parseDouble(list.get(1).toString()));
            envmonitor.setNo2(Double.parseDouble(list.get(2).toString()));
            envmonitor.setO3(Double.parseDouble(list.get(3).toString()));
            envmonitor.setSo2(Double.parseDouble(list.get(4).toString()));
            envmonitor.setPm10(Double.parseDouble(list.get(5).toString()));
            envmonitor.setDate(LocalDateTime.parse(list.get(6).toString(), formatter));
            System.out.println(envmonitor.toJson());
            envmonitors.add(envmonitor);
        }
        return envmonitors;
    }

    public List<Double> settingCoords(String response){
        Gson gson = new Gson();
        System.out.println(response);
        JsonArray jsonArray = gson.fromJson(response, JsonArray.class);
        System.out.println(jsonArray);
        if (jsonArray != null && jsonArray.size() > 0) {
            JsonObject firstResult = jsonArray.get(0).getAsJsonObject();
            double lat = firstResult.get("lat").getAsDouble();
            double lon = firstResult.get("lon").getAsDouble();
            return List.of(lat, lon);
        } else{
            throw new RuntimeException("JSON response does not contain a valid array");
        }
    }

    public List<Double> settingAirData(String response){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
            
        double aqi = jsonObject.getAsJsonArray("list").get(0).getAsJsonObject().getAsJsonObject("main").get("aqi").getAsDouble();
        double co = jsonObject.getAsJsonArray("list").get(0).getAsJsonObject().getAsJsonObject("components").get("co").getAsDouble();
        double no2 = jsonObject.getAsJsonArray("list").get(0).getAsJsonObject().getAsJsonObject("components").get("no2").getAsDouble();
        double o3 = jsonObject.getAsJsonArray("list").get(0).getAsJsonObject().getAsJsonObject("components").get("o3").getAsDouble();
        double so2 = jsonObject.getAsJsonArray("list").get(0).getAsJsonObject().getAsJsonObject("components").get("so2").getAsDouble();
        double pm10 = jsonObject.getAsJsonArray("list").get(0).getAsJsonObject().getAsJsonObject("components").get("pm10").getAsDouble();
        return List.of(aqi, co, no2, o3, so2, pm10);
    }

    public List<List<Object>> settingFutureAirData(String response){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
        JsonArray jsonArray = jsonObject.getAsJsonArray("list");
        List<List<Object>> airQuality = new ArrayList<List<Object>>();
        for(int i = 0; i < jsonArray.size(); i++){
            if(i%20 ==0){
                int epoch = jsonArray.get(i).getAsJsonObject().get("dt").getAsInt();
                String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date (epoch*1000L)); 
                double aqi = jsonArray.get(i).getAsJsonObject().getAsJsonObject("main").get("aqi").getAsDouble();
                double co = jsonArray.get(i).getAsJsonObject().getAsJsonObject("components").get("co").getAsDouble();
                double no2 = jsonArray.get(i).getAsJsonObject().getAsJsonObject("components").get("no2").getAsDouble();
                double o3 = jsonArray.get(i).getAsJsonObject().getAsJsonObject("components").get("o3").getAsDouble();
                double so2 = jsonArray.get(i).getAsJsonObject().getAsJsonObject("components").get("so2").getAsDouble();
                double pm10 = jsonArray.get(i).getAsJsonObject().getAsJsonObject("components").get("pm10").getAsDouble();
                airQuality.add(List.of(aqi, co, no2, o3, so2, pm10, date));
            }
            
        }
        return airQuality;
    }

}
