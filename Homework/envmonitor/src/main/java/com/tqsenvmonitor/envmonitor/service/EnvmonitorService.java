package com.tqsenvmonitor.envmonitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.tqsenvmonitor.envmonitor.cache.CacheData;
import com.tqsenvmonitor.envmonitor.model.Envmonitor;
import com.tqsenvmonitor.envmonitor.repository.EnvmonitorRepository;

@Service
public class EnvmonitorService {
    @Autowired
    private EnvmonitorRepository envmonitorRepository;

    private CacheData cacheData;

    public List<Envmonitor> getAllEnvmonitors() {
        return envmonitorRepository.findAll();
    }

    public Envmonitor getEnvmonitorById(long id) {
        return envmonitorRepository.findById(id).get();
    }

    public Envmonitor getEnvmonitorByCityName(String cityName) {
        return envmonitorRepository.findByCityName(cityName);
    }

    public void saveEnvmonitor(Envmonitor envmonitor) {
        envmonitorRepository.save(envmonitor);
    }

    public void deleteEnvmonitor(long id) {
        envmonitorRepository.deleteById(id);
    }

    public void deleteAllEnvmonitors() {
        envmonitorRepository.deleteAll();
    }

    public Envmonitor updateEnvmonitor(Envmonitor envmonitor){
        Envmonitor existingEnvmonitor = envmonitorRepository.findById(envmonitor.getId()).orElse(null);
        existingEnvmonitor.setCityName(envmonitor.getCityName());
        existingEnvmonitor.setDate(envmonitor.getDate());
        existingEnvmonitor.setNo2(envmonitor.getNo2());
        existingEnvmonitor.setSo2(envmonitor.getSo2());
        existingEnvmonitor.setCo(envmonitor.getCo());
        existingEnvmonitor.setO3(envmonitor.getO3());
        existingEnvmonitor.setPm10(envmonitor.getPm10());
        existingEnvmonitor.setAqi(envmonitor.getAqi());
        return envmonitorRepository.save(existingEnvmonitor);
    }
}
