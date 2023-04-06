package com.tqsenvmonitor.envmonitor.repository;

import com.tqsenvmonitor.envmonitor.model.Envmonitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param; 


@Repository
public interface EnvmonitorRepository extends JpaRepository<Envmonitor, Long> {
    Envmonitor findByCityName( String cityName);
    boolean existsByCityName(String cityName);
}
