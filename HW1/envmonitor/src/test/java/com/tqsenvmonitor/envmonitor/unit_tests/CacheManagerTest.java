package com.tqsenvmonitor.envmonitor.unit_tests;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.tqsenvmonitor.envmonitor.cache.CacheData;
import com.tqsenvmonitor.envmonitor.model.Envmonitor;

public class CacheManagerTest {
    @Test
    void testGetFromCache() {
        CacheData cacheData = new CacheData();
        Envmonitor envmonitor = new Envmonitor();
        envmonitor.setId(1);
        envmonitor.setCityName("Lisboa");
        envmonitor.setDate(LocalDateTime.now());
        cacheData.put("Lisboa", envmonitor);
        Envmonitor result = cacheData.getFromCache("Lisboa");
        Assertions.assertEquals(envmonitor, result);
    }

    @Test
    void getAllEnvmonitors(){
        CacheData cacheData = new CacheData();
        Envmonitor envmonitor = new Envmonitor();
        envmonitor.setId(1);
        envmonitor.setCityName("Lisboa");
        envmonitor.setDate(LocalDateTime.now());
        cacheData.put("Lisboa", envmonitor);
        Envmonitor envmonitor2 = new Envmonitor();
        envmonitor2.setId(2);
        envmonitor2.setCityName("Porto");
        envmonitor2.setDate(LocalDateTime.now());
        cacheData.put("Porto", envmonitor2);
        List<Envmonitor> result = cacheData.getCacheList();
        Assertions.assertEquals(envmonitor, result.get(0));
        Assertions.assertEquals(envmonitor2, result.get(1));
    }

    @Test 
    void checkIfClears(){
        CacheData cacheData = new CacheData();
        Envmonitor envmonitor = new Envmonitor();
        envmonitor.setId(1);
        envmonitor.setCityName("Lisboa");
        envmonitor.setDate(LocalDateTime.now());
        cacheData.put("Lisboa", envmonitor);
        Envmonitor envmonitor2 = new Envmonitor();
        envmonitor2.setId(2);
        envmonitor2.setCityName("Porto");
        envmonitor2.setDate(LocalDateTime.now());
        cacheData.put("Porto", envmonitor2);
        cacheData.clearCache();
        List<Envmonitor> result = cacheData.getCacheList();
        Assertions.assertEquals(0, result.size());
    }
}
