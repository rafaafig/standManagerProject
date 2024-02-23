package StandManagerProject.standManager.Dto;

import StandManagerProject.standManager.Enums.CarEnums;
import StandManagerProject.standManager.Models.Brand;
import StandManagerProject.standManager.Models.Model;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.boot.Banner;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    Model model;

    String licensePlate;

    @Enumerated(EnumType.STRING)
    CarEnums.Traction traction;

    @Enumerated(EnumType.STRING)
    CarEnums.Country country;

    @Enumerated(EnumType.STRING)
    CarEnums.Seats seats;

    @Enumerated(EnumType.STRING)
    CarEnums.Fuel fuel;

    @Enumerated(EnumType.STRING)
    CarEnums.Type type;

    @Enumerated(EnumType.STRING)
    CarEnums.Doors doors;

    @Enumerated(EnumType.STRING)
    CarEnums.Status status;

}
