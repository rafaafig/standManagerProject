package StandManagerProject.standManager.Converters;

import StandManagerProject.standManager.Dto.CarDto;
import StandManagerProject.standManager.Models.Car;

public class CarConverter {

    public static CarDto toDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .model(car.getModel())
                .traction(car.getTraction())
                .country(car.getCountry())
                .seats(car.getSeats())
                .fuel(car.getFuel())
                .type(car.getType())
                .doors(car.getDoors())
                .build();
    }

    private Car convertToCar(CarDto carDto) {
        Car car = new Car();
        car.setId(carDto.getId());
        car.setModel(carDto.getModel());
        car.setTraction(carDto.getTraction());
        car.setCountry(carDto.getCountry());
        car.setSeats(carDto.getSeats());
        car.setFuel(carDto.getFuel());
        car.setType(carDto.getType());
        car.setDoors(carDto.getDoors());
        return car;
    }
}
