package StandManagerProject.standManager.Services;

import StandManagerProject.standManager.Models.Car;
import StandManagerProject.standManager.Repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }

    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car updatedCar) {
        return carRepository.findById(id)
                .map(car -> {
                    car.setModelName(updatedCar.getModelName());
                    car.setMakeYear(updatedCar.getMakeYear());
                    car.setColor(updatedCar.getColor());
                    car.setPrice(updatedCar.getPrice());
                    car.setAvailability(updatedCar.isAvailability());
                    car.setPurchaseDate(updatedCar.getPurchaseDate());
                    car.setSaleDate(updatedCar.getSaleDate());
                    return carRepository.save(car);
                })
                .orElseGet(() -> {
                    updatedCar.setId(id);
                    return carRepository.save(updatedCar);
                });
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
