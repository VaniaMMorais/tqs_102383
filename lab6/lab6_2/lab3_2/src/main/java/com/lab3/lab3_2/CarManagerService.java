package com.lab3.lab3_2;

import java.util.List;
import java.util.Optional;

import com.lab3.lab3_2.Car;
import com.lab3.lab3_2.CarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarManagerService {
    
    @Autowired
    private CarRepository carRepository;

    public Car save(Car car){
        return carRepository.save(car);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long carId){
        Car car = carRepository.findByCarId(carId);
        if (car != null)
            return Optional.of(car);

        return Optional.empty();
    }
}
