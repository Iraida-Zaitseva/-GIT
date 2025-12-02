package web.service;

import web.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Override
    public List<Car> getCars(int count) {
        List<Car> allCars = new ArrayList<>();

        allCars.add(new Car("Toyota", "Camry", 2020));
        allCars.add(new Car("Honda", "Civic", 2019));
        allCars.add(new Car("Ford", "Mustang", 2021));
        allCars.add(new Car("BMW", "X5", 2022));
        allCars.add(new Car("Mercedes", "C-Class", 2018));

        if (count > 5) count = 5;
        if (count < 0) count = 0;

        return allCars.subList(0, count);
    }
}
