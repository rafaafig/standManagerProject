package StandManagerProject.standManager.Services;

import StandManagerProject.standManager.Converters.CarConverter;
import StandManagerProject.standManager.Dto.CarDto;
import StandManagerProject.standManager.Enums.CarEnums;
import StandManagerProject.standManager.Exceptions.BrandNotFoundException;
import StandManagerProject.standManager.Exceptions.SellerNotFoundException;
import StandManagerProject.standManager.Models.Car;
import StandManagerProject.standManager.Models.Model;
import StandManagerProject.standManager.Models.Seller;
import StandManagerProject.standManager.Repositories.CarRepository;
import StandManagerProject.standManager.Repositories.ModelRepository;
import StandManagerProject.standManager.Repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private SellerRepository sellerRepository;


    public Page<Car> getAllCars(Pageable pageable) {
        return carRepository.findAll(pageable);
    }


    public Optional<Car> getCarById(Long id) {
        return carRepository.findById(id);
    }


    public Car addCar(Car car) {

      Optional<Model> modelOptional = modelRepository.findById(car.getModel().getId());

//      Optional<Seller> sellerOptional = sellerRepository.findById(car.getSeller().getId());

        if (!modelOptional.isPresent()) {

            throw new BrandNotFoundException("Brand with id " + car.getModel().getBrand().getId() + " not found");
        }
//        if (!sellerOptional.isPresent()) {
//
//           throw new SellerNotFoundException("Brand with id " + car.getModel().getBrand().getId() + " not found");
//        }

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

//    public Page<Car> availableCars(int page, int size, String sort){
//        return carRepository.findCarsByStatus(CarEnums.Status.IN_STOCK.toString(), PageRequest.of(page, size, Sort.by(sort)));
//    }
//
//    public Page<Car> soldCars(int page, int size, String sort){
//        return carRepository.findCarsByStatus(CarEnums.Status.SOLD.toString(), PageRequest.of(page, size, Sort.by(sort)));
//    }


    //also get sale price-
    public Car markCarAsSold(Long carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + carId));

        car.setStatus(CarEnums.Status.SOLD);

        return carRepository.save(car);
    }
}

