package com.tqsenvmonitor.envmonitor.cache;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tqsenvmonitor.envmonitor.model.Envmonitor;

public class CacheData {
    private static final int MAX_CACHE_SIZE = 10000;
    private static final Duration CACHE_TTL = Duration.ofMinutes(30);
    private final Map<String, Envmonitor> cache = new HashMap<>();
    private int requestsCount = 0;
    private int hitsCount = 0;
    private int missesCount = 0;

    public synchronized Envmonitor getFromCache(String key) {
        Envmonitor envmonitor = cache.get(key);     
        // if (envmonitor != null && envmonitor.getExpiryTime().isAfter(LocalDateTime.now())) {
        //     hitsCount++;
        // } else {
        //     cache.remove(key);
        //     envmonitor = null;
        //     missesCount++;
        // }
        // requestsCount++;   
        return envmonitor;
    }

    // public synchronized Envmonitor existsInCache(String key){
    //     Envmonitor envmonitor = cache.get(key); 
    //     return envmonitor;
    // }

    public synchronized void put(String key, Envmonitor envmonitor) {   
        envmonitor.setCityName(key);
        envmonitor.setDate(LocalDateTime.now());
        envmonitor.setExpiryTime(LocalDateTime.now().plus(CACHE_TTL));
        cache.put(key, envmonitor);
    }

    public synchronized void clearCache() {
        cache.clear();
        requestsCount = 0;
        hitsCount = 0;
        missesCount = 0;
    }

    public synchronized int getCacheSize() {
        return cache.size();
    }

    public List<Envmonitor> getCacheList() {
        return new ArrayList<>(cache.values());
    }

    public int getRequestsCount() {
        return requestsCount;
    }

    public int getHitsCount() {
        return hitsCount;
    }

    public int getMissesCount() {
        return missesCount;
    }

    public void addMisse() {
        missesCount++;
    }

    public void addHit() {
        hitsCount++;
    }

    public void addRequest() {
        requestsCount++;
    }
    
}