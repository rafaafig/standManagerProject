package StandManagerProject.standManager.Dto;

import StandManagerProject.standManager.Enums.CarEnums;
import StandManagerProject.standManager.Models.Brand;
import StandManagerProject.standManager.Models.Model;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.Banner;

@Builder
@Getter
@Setter
public class CarDto {

    Long id;

    Model model;

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

}
