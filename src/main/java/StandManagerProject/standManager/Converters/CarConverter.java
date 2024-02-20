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
}
