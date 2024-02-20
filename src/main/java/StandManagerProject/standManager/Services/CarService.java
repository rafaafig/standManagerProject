package StandManagerProject.standManager.Services;

import StandManagerProject.standManager.Enums.CarEnums;
import StandManagerProject.standManager.Exceptions.BrandNotFoundException;
import StandManagerProject.standManager.Models.Brand;
import StandManagerProject.standManager.Models.Car;
import StandManagerProject.standManager.Models.Model;
import StandManagerProject.standManager.Models.Seller;
import StandManagerProject.standManager.Repositories.BrandRepository;
import StandManagerProject.standManager.Repositories.CarRepository;
import StandManagerProject.standManager.Repositories.ModelRepository;
import StandManagerProject.standManager.Repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private SellerRepository sellerRepository;


    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }


    public Car addCar(Car car) {

        Optional<Model> modelOptional = modelRepository.findById(car.getModel().getId());

        Optional<Seller> sellerOptional = sellerRepository.findById(car.getSeller().getId());

        if (!modelOptional.isPresent()) {

            throw new BrandNotFoundException("Brand with id " + car.getModel().getBrand().getId() + " not found");
        }

        return carRepository.save(car);
    }


    public Car updateCar(Long id, Car updatedCar) {
        if (carRepository.existsById(id)) {
            updatedCar.setId(id);
            return carRepository.save(updatedCar);
        } else {
            return null;
        }
    }

    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }


    public Car changeCarStatus(Long carId, CarEnums.Status newStatus) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + carId));
        car.setStatus(newStatus);
        return carRepository.save(car);
    }
}

