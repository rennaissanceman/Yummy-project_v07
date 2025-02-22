package pl.yummy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDeliveryAreaDTO {

//    Uproszczona reprezentacja obszaru dostawy

    Long availableDeliveryAreaId;
    Long restaurantId;
    AddressDTO address;

    /*
    (opcjonalnie) uproszczone informacje – np. liczba zamówień czy dostaw,
    aby móc szybko ocenić, czy obszar ma aktywność
    */
}
