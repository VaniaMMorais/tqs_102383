package com.tqsenvmonitor.envmonitor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.json.JSONObject;


import java.time.Duration;
import java.time.LocalDateTime;

import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
public class Envmonitor {
    private long id;
    private String cityName;
    private LocalDateTime date;
    private Double no2;
    private Double so2;
    private Double co;
    private Double o3;
    private Double pm10;
    private Integer aqi; // Air Quality Index (1-5, onde 1 é o melhor)
    private LocalDateTime expiryTime;

    private static final Duration CACHE_TTL = Duration.ofMinutes(30);

    public Envmonitor() {
    }

    public Envmonitor(String cityName, Double no2, Double so2, Double co, Double o3, Double pm10, Integer aqi) {
        this.cityName = cityName;
        this.date = LocalDateTime.now();
        this.no2 = no2;
        this.so2 = so2;
        this.co = co;
        this.o3 = o3;
        this.pm10 = pm10;
        this.aqi = aqi;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getNo2() {
        return no2;
    }

    public void setNo2(Double no2) {
        this.no2 = no2;
    }

    public Double getSo2() {
        return so2;
    }

    public void setSo2(Double so2) {
        this.so2 = so2;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public Double getO3() {
        return o3;
    }

    public void setO3(Double o3) {
        this.o3 = o3;
    }

    public Double getPm10() {
        return pm10;
    }

    public void setPm10(Double pm10) {
        this.pm10 = pm10;
    }

    public Integer getAqi() {
        return aqi;
    }

    public void setAqi(Integer aqi) {
        this.aqi = aqi;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("id", this.getId());
        json.put("cityName", this.getCityName());
        json.put("date", this.getDate().toString());
        json.put("no2", this.getNo2());
        json.put("so2", this.getSo2());
        json.put("co", this.getCo());
        json.put("o3", this.getO3());
        json.put("pm10", this.getPm10());
        json.put("aqi", this.getAqi());
        return json;
    }

    public LocalDateTime getExpiryTime() {
        return date.plus(CACHE_TTL);
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }
}
