package com.tqsenvmonitor.envmonitor.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tqsenvmonitor.envmonitor.model.Envmonitor;
import com.tqsenvmonitor.envmonitor.repository.EnvmonitorRepository;
import com.tqsenvmonitor.envmonitor.service.EnvmonitorService;
import com.tqsenvmonitor.envmonitor.cache.CacheData;


@RestController
public class EnvmonitorController {
    @Autowired
    private EnvmonitorService envmonitorService;

    @Autowired
    private EnvmonitorRepository envmonitorRepository;

    CacheData cacheData = new CacheData();

    @GetMapping("/airquality/{city}")
    public Envmonitor getAirQuality(@PathVariable String city) {
        Envmonitor envmonitor = cacheData.getFromCache(city);
        if (envmonitor == null) {
            return null;
        }
        return envmonitor;
    }

    @PostMapping("/airquality/{city}")
    public Envmonitor addAirQuality(@PathVariable String city, @RequestBody Envmonitor envmonitor) {
        cacheData.put(city, envmonitor);
        return envmonitor;
    }

    @GetMapping("/checkcity/{city}")
    public boolean checkCache(@PathVariable String city) {
        Envmonitor cache = cacheData.getFromCache(city);
        if (cache == null) {
            cacheData.addMisse();
        } else {
            cacheData.addHit();
        }
        cacheData.addRequest();
        return cache != null;
    }

    @GetMapping("/allCache")
    public List<Envmonitor> getCacheData() {
        return cacheData.getCacheList();
    }

    @GetMapping("/cacheStatus")
    public List<Double> getCacheStatus() {
        List<Double> cacheStatus = new ArrayList<>();
        cacheStatus.add((double) cacheData.getRequestsCount());
        cacheStatus.add((double) cacheData.getHitsCount());
        cacheStatus.add((double) cacheData.getMissesCount());
        return cacheStatus;
    }



}
