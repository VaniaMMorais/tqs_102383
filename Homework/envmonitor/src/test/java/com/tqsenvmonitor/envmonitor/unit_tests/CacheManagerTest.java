package com.tqsenvmonitor.envmonitor.unit_tests;

import java.time.LocalDateTime;

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
}
