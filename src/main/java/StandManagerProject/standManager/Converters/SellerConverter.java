package StandManagerProject.standManager.Converters;

import StandManagerProject.standManager.Dto.SellerDto;
import StandManagerProject.standManager.Models.Seller;

public class SellerConverter {

    public static SellerDto toDto (Seller seller){
        return SellerDto.builder()
                .name(seller.getName())
                .build();
    }
}
