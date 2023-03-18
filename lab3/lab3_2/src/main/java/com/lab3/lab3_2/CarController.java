package com.lab3.lab3_2;

import com.lab3.lab3_2.Car;
import com.lab3.lab3_2.CarManagerService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api")
public class CarController {
    
    @Autowired
    private CarManagerService carManagerService;

    @PostMapping("/car")
    public ResponseEntity<Car> creatCar(Car car){
        return new ResponseEntity<Car>(carManagerService.save(car), HttpStatus.CREATED);
    }

    @GetMapping("/allCars")
    public List<Car> getAllCars(){
        return carManagerService.getAllCars();
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Car> creatCar(@PathVariable Long carId){
        return ResponseEntity.of(carManagerService.getCarDetails(carId));
    }
}
