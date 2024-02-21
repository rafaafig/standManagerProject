package StandManagerProject.standManager.Controllers;

import StandManagerProject.standManager.Converters.CarConverter;
import StandManagerProject.standManager.Dto.CarDto;
import StandManagerProject.standManager.Enums.CarEnums;
import StandManagerProject.standManager.Models.Car;
import StandManagerProject.standManager.Models.Seller;
import StandManagerProject.standManager.Services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public CollectionModel<EntityModel<CarDto>> getAllCars(@RequestParam(name = "page", defaultValue = "0") int page,
                                                           @RequestParam(name = "size", defaultValue = "2") int size,
                                                           @RequestParam(name = "sort", defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Car> carsPage = carService.getAllCars(pageable);

        List<EntityModel<CarDto>> carModels = carsPage.getContent().stream()
                .map(car -> EntityModel.of(CarConverter.toDto(car),
                        linkTo(methodOn(CarController.class).getCarById(car.getId())).withSelfRel(),
                        linkTo(methodOn(CarController.class).updateCar(car.getId(), car)).withRel("update"),
                        linkTo(methodOn(CarController.class).deleteCar(car.getId())).withRel("delete")))
                .toList();

        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(CarController.class).getAllCars(page, size, sort)).withSelfRel());

        if (carsPage.hasNext()) {
            Link nextLink = linkTo(methodOn(CarController.class).getAllCars(page + 1, size, sort)).withRel("next");
            links.add(nextLink);
        }
        if (carsPage.hasPrevious()) {
            Link prevLink = linkTo(methodOn(CarController.class).getAllCars(page - 1, size, sort)).withRel("previous");
            links.add(prevLink);
        }

        return CollectionModel.of(carModels, links);
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

    @GetMapping("/available")
    public CollectionModel<EntityModel<CarDto>> availableCars(@RequestParam(name = "page", defaultValue = "0") int page,
                                                              @RequestParam(name = "size", defaultValue = "2") int size,
                                                              @RequestParam(name = "sort", defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Car> carsPage = carService.availableCars(page, size, sort);

        List<EntityModel<CarDto>> carModels = carsPage.getContent().stream()
                .map(car -> EntityModel.of(CarConverter.toDto(car),
                        linkTo(methodOn(CarController.class).getCarById(car.getId())).withSelfRel(),
                        linkTo(methodOn(CarController.class).updateCar(car.getId(), car)).withRel("update"),
                        linkTo(methodOn(CarController.class).deleteCar(car.getId())).withRel("delete")))
                .toList();

        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(CarController.class).availableCars(page, size, sort)).withSelfRel());

        if (carsPage.hasNext()) {
            Link nextLink = linkTo(methodOn(CarController.class).availableCars(page + 1, size, sort)).withRel("next");
            links.add(nextLink);
        }
        if (carsPage.hasPrevious()) {
            Link prevLink = linkTo(methodOn(CarController.class).availableCars(page - 1, size, sort)).withRel("previous");
            links.add(prevLink);
        }

        return CollectionModel.of(carModels, links);
    }

    @GetMapping("/sold")
    public CollectionModel<EntityModel<CarDto>> soldCars(@RequestParam(name = "page", defaultValue = "0") int page,
                                                              @RequestParam(name = "size", defaultValue = "2") int size,
                                                              @RequestParam(name = "sort", defaultValue = "id") String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Car> carsPage = carService.soldCars(page, size, sort);

        List<EntityModel<CarDto>> carModels = carsPage.getContent().stream()
                .map(car -> EntityModel.of(CarConverter.toDto(car),
                        linkTo(methodOn(CarController.class).getCarById(car.getId())).withSelfRel(),
                        linkTo(methodOn(CarController.class).updateCar(car.getId(), car)).withRel("update"),
                        linkTo(methodOn(CarController.class).deleteCar(car.getId())).withRel("delete")))
                .toList();

        List<Link> links = new ArrayList<>();
        links.add(linkTo(methodOn(CarController.class).availableCars(page, size, sort)).withSelfRel());

        if (carsPage.hasNext()) {
            Link nextLink = linkTo(methodOn(CarController.class).availableCars(page + 1, size, sort)).withRel("next");
            links.add(nextLink);
        }
        if (carsPage.hasPrevious()) {
            Link prevLink = linkTo(methodOn(CarController.class).availableCars(page - 1, size, sort)).withRel("previous");
            links.add(prevLink);
        }

        return CollectionModel.of(carModels, links);
    }

    //carService.markCarAsSold(carId);
    @PutMapping("/{id}/sold")
    public ResponseEntity<Car> markCarAsSold(@PathVariable Long id) {
        Car updatedCar = carService.markCarAsSold(id);
        EntityModel<Car> model = EntityModel.of(updatedCar,
                linkTo(methodOn(CarController.class).getCarById(id)).withSelfRel(),
                linkTo(methodOn(CarController.class).updateCar(id, updatedCar)).withRel("update"),
                linkTo(methodOn(CarController.class).deleteCar(id)).withRel("delete"));
        return ResponseEntity.ok(updatedCar);
    }
}


