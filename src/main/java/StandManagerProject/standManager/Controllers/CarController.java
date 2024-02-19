package StandManagerProject.standManager.Controllers;

import StandManagerProject.standManager.Converters.CarConverter;
import StandManagerProject.standManager.Dto.CarDto;
import StandManagerProject.standManager.Enums.CarEnums;
import StandManagerProject.standManager.Models.Car;
import StandManagerProject.standManager.Models.Seller;
import StandManagerProject.standManager.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("stand/cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<CarDto>>> getAllCars() {
        List<Car> cars = carService.getAllCars();
        List<EntityModel<CarDto>> carsModels = cars.stream()
                .map(car -> EntityModel.of(CarConverter.toDto(car),
                        linkTo(methodOn(CarController.class).getCarById(car.getId())).withSelfRel(),
                        linkTo(methodOn(CarController.class).updateCar(car.getId(), car)).withRel("update"),
                        linkTo(methodOn(CarController.class).deleteCar(car.getId())).withRel("delete")))
                .toList();
        return ResponseEntity.ok(carsModels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CarDto>> getCarById(@PathVariable Long id) {
        return carService.getCarById(id)
                .map(car -> {
                    CarDto carDto = CarConverter.toDto(car);
                    EntityModel<CarDto> model = EntityModel.of(carDto,
                            linkTo(methodOn(CarController.class).getCarById(id)).withSelfRel(),
                            linkTo(methodOn(CarController.class).updateCar(id, car)).withRel("update"),
                            linkTo(methodOn(CarController.class).deleteCar(id)).withRel("delete"));
                    return ResponseEntity.ok(model);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<Car>> addCar(@RequestBody Car car) {
        Car savedCar = carService.addCar(car);
        EntityModel<Car> model = EntityModel.of(savedCar,
                linkTo(methodOn(CarController.class).getCarById(savedCar.getId())).withSelfRel());
        return ResponseEntity.status(HttpStatus.CREATED).body(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Car>> updateCar(@PathVariable Long id, @RequestBody Car car) {
        Car updatedCar = carService.updateCar(id, car);
        EntityModel<Car> model = EntityModel.of(updatedCar,
                linkTo(methodOn(CarController.class).getCarById(id)).withSelfRel(),
                linkTo(methodOn(CarController.class).updateCar(id, car)).withRel("update"),
                linkTo(methodOn(CarController.class).deleteCar(id)).withRel("delete"));
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<Car> changeCarStatus(@PathVariable Long id, @RequestParam CarEnums.Status newStatus) {
        Car updatedCar = carService.changeCarStatus(id, newStatus);
        EntityModel<Car> model = EntityModel.of(updatedCar,
                linkTo(methodOn(CarController.class).getCarById(id)).withSelfRel(),
                linkTo(methodOn(CarController.class).updateCar(id, updatedCar)).withRel("update"),
                linkTo(methodOn(CarController.class).deleteCar(id)).withRel("delete"));
        return ResponseEntity.ok(updatedCar);
    }
}
