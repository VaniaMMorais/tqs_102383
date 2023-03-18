package com.lab3.lab3_2;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{
    public Car findByCarId(Long carId);
    public List<Car> findAll();
    
}
