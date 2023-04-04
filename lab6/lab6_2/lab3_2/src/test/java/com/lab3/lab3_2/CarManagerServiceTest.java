package com.lab3.lab3_2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.lab3.lab3_2.Car;
import com.lab3.lab3_2.CarRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarManagerServiceTest {

    @Mock(lenient = true)
    private CarRepository carRepository;
  
    @InjectMocks
    private CarManagerService carManagerService;

    

    @BeforeEach
    public void setUp() {
        // car variables to be used by test functions
        Car c1 = new Car("car1", "m1");
        Car c2 = new Car("car2", "m2");
        Car c3 = new Car("car3", "m3");
        
        List<Car> allCars = Arrays.asList(c1, c2, c3);

        Mockito.when(carRepository.findAll()).thenReturn(allCars);
        Mockito.when(carRepository.findByCarId(c1.getCarId())).thenReturn(c1);
        Mockito.when(carRepository.findByCarId(c3.getCarId())).thenReturn(c3);
        Mockito.when(carRepository.findByCarId(-99L)).thenReturn(null);
    }

    @Test
    public void testSaveCar() {
        Car car = new Car("toyota", "corolla");

        Mockito.when(carRepository.save(car)).thenReturn(car);

        assertThat(carManagerService.save(car).getModel()).isEqualTo("corolla");
        
        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void testReturnAllCars() {
        List<Car> allCars = carManagerService.getAllCars();

        // check if size is equal to number of cars
        assertThat(allCars.size()).isEqualTo(3);

        assertThat(allCars.get(0).getModel()).isEqualTo("m1");
        assertThat(allCars.get(1).getModel()).isEqualTo("m2");
        assertThat(allCars.get(2).getModel()).isEqualTo("m3");

        verify(carRepository, VerificationModeFactory.times(1)).findAll();
    }

    @Test
    public void testCarNotExists() {
        assertThat(carManagerService.getCarDetails(-1000L)).isEmpty();
        Mockito.verify(carRepository, times(1)).findByCarId(-1000L);
    }
}
