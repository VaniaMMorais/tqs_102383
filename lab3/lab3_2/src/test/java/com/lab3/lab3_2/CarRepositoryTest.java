package com.lab3.lab3_2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
  
    @Autowired
    private CarRepository carRepository;

    @Test
    public void testValidCarId() {
        Car c1 = new Car("car1", "model1");
        entityManager.persistAndFlush(c1);

        Car carDb = carRepository.findByCarId(c1.getCarId());
        assertThat(carDb).isNotNull();
        assertThat(carDb.getModel()).isEqualTo("model1");
    }

    @Test
    public void testInvalidCarId() {
        Car carDb = carRepository.findByCarId(100L);
        assertThat(carDb).isNull();
    }

    @Test
    public void testAllCars() {
        Car c1 = new Car("car1", "model1");
        Car c2 = new Car("car2", "model2");
        Car c3 = new Car("car3", "model3");
        Car c4 = new Car("car4", "model4");

        entityManager.persist(c1);
        entityManager.persist(c2);
        entityManager.persist(c3);
        entityManager.persist(c4);
        entityManager.flush();

        List<Car> cars = carRepository.findAll();
        assertThat(cars).hasSize(4).extracting(Car::getModel).containsOnly(c1.getModel(), c2.getModel(), c3.getModel(), c4.getModel());
    }
}
